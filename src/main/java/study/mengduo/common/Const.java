package study.mengduo.common;

/**
 * @aothor mengDuo
 * @date 2020/3/14 13:27
 */
public class Const {

    public final static String CURRENT_USER = "currentUser";
    public final static String USER_NAME = "username";
    public final static String Email = "email";

    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户

        int ROLE_ADMIN =1; //管理员
    }

    public interface Cart{
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
}
