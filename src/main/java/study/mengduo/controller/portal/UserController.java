package study.mengduo.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.mengduo.common.Const;
import study.mengduo.common.ResponseCode;
import study.mengduo.common.ServerResponse;
import study.mengduo.pojo.User;
import study.mengduo.service.IUserService;

import javax.servlet.http.HttpSession;


/**
 * @aothor mengDuo
 * @date 2020/3/14 09:59
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;


    @PostMapping("login")
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @PostMapping("logout")
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.creatBySucess();
    }

    @PostMapping("register")
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    //校验用户名密码是否存在
    @PostMapping("check_valid")
    public ServerResponse<String> checkValid(String str, String type){
        return iUserService.checkValid(str,type);
    }

    @PostMapping("getUserInfo")
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null){
            return ServerResponse.creatBySuccess(user);
        }
        return ServerResponse.creatByErrorMessage("获取用户信息失败");
    }

    //根据用户名获取问题
    @PostMapping("forgetGetQuestion")
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.getQuestionByUsername(username);
    }

    //校验问题的答案是否正确
    @PostMapping("forgetCheckAnswer")
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username, question, answer);
    }

    @PostMapping("forgetResetPassword")
    public ServerResponse<String> forgetResetPassword(String username,String newPassword,String token){
        return iUserService.forgetResetPassword(username,newPassword,token);
    }

    @PostMapping("resetPassword")
    public ServerResponse<String> resetPassword(String oldPassword,String newPassword,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.creatByErrorMessage("用户未登录，请先登录");
        }
        return iUserService.resetPassword(oldPassword, newPassword,user);
    }


    @PostMapping("updateUserInfo")
    public ServerResponse<User> updateUserInfo(HttpSession session, User user){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser==null){
            return ServerResponse.creatByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateUserInfo(user);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response);
        }
        return response;
    }

    @PostMapping("getUserInfoDetail")
    public ServerResponse<User> getUserInfoDetail(HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"请先登录");
        }
        Integer userId = currentUser.getId();
        return iUserService.getUserInfo(userId);

    }



}
