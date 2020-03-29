package study.mengduo.service;

import study.mengduo.common.ServerResponse;
import study.mengduo.pojo.User;

/**
 * @aothor mengDuo
 * @date 2020/3/14 10:02
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> getQuestionByUsername(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetResetPassword(String username,String newPassword,String token);

    ServerResponse<String> resetPassword(String oldPassword,String newPassword,User user);

    ServerResponse<User> updateUserInfo(User user);

    ServerResponse<User> getUserInfo(Integer userId);

    ServerResponse<User> checkIsAdmin(User user);
}
