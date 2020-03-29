package study.mengduo.mapper;

import org.apache.ibatis.annotations.Param;
import study.mengduo.pojo.User;

/**
 * @aothor mengDuo
 * @date 2020/3/14 11:10
 */


public interface UserMapper {

    int checkUserName(String username);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkEmail(String email);

    int insert(User user);

    String getQuestionByUsername(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePassword(@Param("username")String username, @Param("newPassword") String newPassword);

    int checkPassword(@Param("oldPassword") String oldPassword, @Param("userId") Integer userId);

    int updateByPrimaryKeySelective(User user);

    int checkEmailByUerId(@Param("email") String email,@Param("userId") Integer userId);

    User selectByPrimaryKey(Integer userId);

}
