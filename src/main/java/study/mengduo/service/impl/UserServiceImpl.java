package study.mengduo.service.impl;



import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.mengduo.common.Const;
import study.mengduo.common.ServerResponse;
import study.mengduo.common.TokenCache;
import study.mengduo.mapper.UserMapper;
import study.mengduo.pojo.User;
import study.mengduo.service.IUserService;
import study.mengduo.util.MD5Util;

import java.util.UUID;

/**
 * @aothor mengDuo
 * @date 2020/3/14 11:07
 */

@Slf4j
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUserName(username);
        if (resultCount == 0){
            return ServerResponse.creatByErrorMessage("用户名不存在");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null){
            return ServerResponse.creatByErrorMessage("密码错误");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.creatBySuccess("登录成功",user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        ServerResponse<String> validResponse = this.checkValid(user.getUsername(), Const.USER_NAME);
        if (!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = this.checkValid(user.getEmail(), Const.Email);
        if (!validResponse.isSuccess()){
            return validResponse;
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setRole(Const.Role.ROLE_CUSTOMER);

        int result = userMapper.insert(user);
        if (result==0){
            return ServerResponse.creatByErrorMessage("注册失败");
        }

        return ServerResponse.creatByMessageSuccess("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (type.equals(Const.USER_NAME)) {
                int i = userMapper.checkUserName(str);
                if (i > 0) {
                    return ServerResponse.creatByErrorMessage("用户名已存在");
                }
            }
            if (type.equals(Const.Email)) {
                int email = userMapper.checkEmail(str);
                if (email > 0) {
                    return ServerResponse.creatByErrorMessage("邮箱已存在");
                }
            }
        }else {
            return ServerResponse.creatByErrorMessage("参数错误");
        }
        return ServerResponse.creatByMessageSuccess("校验成功");
    }

    @Override
    public ServerResponse<String> getQuestionByUsername(String username) {
        ServerResponse<String> response = this.checkValid(username, Const.USER_NAME);
        if (response.isSuccess()){
            return ServerResponse.creatByErrorMessage("用户名不存在");
        }
        String question = userMapper.getQuestionByUsername(username);
        return ServerResponse.creatBySuccess(question);
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount>0){
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey("token_"+username,forgetToken);
            return ServerResponse.creatByMessageSuccess(forgetToken);
        }
        return ServerResponse.creatByErrorMessage("问题的答案错误");
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String username, String newPassword, String token) {
        ServerResponse<String> response = this.checkValid(username, Const.USER_NAME);
        if (response.isSuccess()){
            return ServerResponse.creatByErrorMessage("用户名不存在");
        }
        String forgetToken = TokenCache.getKey("token_" + username);
        if (org.apache.commons.lang3.StringUtils.isBlank(forgetToken)){
            return ServerResponse.creatByErrorMessage("token失效或不存在");
        }
        if (org.apache.commons.lang3.StringUtils.equals(forgetToken,token)){
            int i = userMapper.updatePassword(username, MD5Util.MD5EncodeUtf8(newPassword));
            if (i>0){
                return ServerResponse.creatByMessageSuccess("密码更新成功");
            }
        }else {
            return ServerResponse.creatByErrorMessage("token错误，请重新获取token");
        }
        return ServerResponse.creatByErrorMessage("修改密码失败！");
    }

    @Override
    public ServerResponse<String> resetPassword(String oldPassword, String newPassword, User user) {
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(oldPassword), user.getId());
        if (resultCount == 0){
            ServerResponse.creatByErrorMessage("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(newPassword));
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result>0){
            return ServerResponse.creatByMessageSuccess("密码重置成功");
        }
        return ServerResponse.creatByErrorMessage("密码更新失败");
    }

    @Override
    public ServerResponse<User> updateUserInfo(User user) {
        int emailCount = userMapper.checkEmailByUerId(user.getEmail(), user.getId());
        if (emailCount>0){
            return ServerResponse.creatByErrorMessage("该邮箱已经存在，请更换邮箱再试！");
        }
        User updateUser = new User();
        updateUser.setUsername(user.getUsername());
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        int updateResult = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateResult>0){
            return ServerResponse.creatBySuccess("更新用户信息成功",updateUser);
        }
        return ServerResponse.creatByErrorMessage("更新用户信息失败");
    }

    @Override
    public ServerResponse<User> getUserInfo(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null){
            return ServerResponse.creatByErrorMessage("用户不存在");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.creatBySuccess(user);
    }

    @Override
    public ServerResponse<User> checkIsAdmin(User user) {
        if (user!=null && user.getRole().intValue()==Const.Role.ROLE_ADMIN){
            return ServerResponse.creatBySucess();
        }
        return ServerResponse.creatByError();
    }
}
