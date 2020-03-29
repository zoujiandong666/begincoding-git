package study.mengduo.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.mengduo.common.Const;
import study.mengduo.common.ServerResponse;
import study.mengduo.pojo.User;
import study.mengduo.service.IUserService;

import javax.servlet.http.HttpSession;

/**
 * @aothor mengDuo
 * @date 2020/3/15 12:39
 */

@RestController
@RequestMapping("/manager/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("login")
    public ServerResponse<User> login(String username, String password, HttpSession httpSession){
        ServerResponse<User> login = iUserService.login(username, password);
        if (login.isSuccess()){
            User user = login.getData();
            if (user.getRole()== Const.Role.ROLE_ADMIN){
                httpSession.setAttribute(Const.CURRENT_USER,user);
            }else {
                return ServerResponse.creatByErrorMessage("不是管理员，无法登录");
            }
        }
        return login;
    }
}
