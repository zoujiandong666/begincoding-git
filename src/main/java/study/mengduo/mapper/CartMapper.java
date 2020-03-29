package study.mengduo.mapper;

import org.apache.ibatis.annotations.Param;
import study.mengduo.pojo.Cart;

import java.util.List;


/**
 * @aothor mengDuo
 * @date 2020/3/26 21:25
 */
public interface CartMapper {

    Cart selectByPrimaryKey(Integer id);

    Cart selectByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    int insert(Cart cart);

    int updateByPrimaryKeySelective(Cart record);

    List<Cart> selectCartByUserId(Integer userId);

    int selectCartProductCheckedStatusByUserId(Integer userId);

    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId")Integer productId);

    int updateByPrimaryKey(Cart record);

    int deleteByUserIdProductIds(@Param("userId") Integer userId,@Param("productIdList")List<String> productIdList);

    int checkedOrUncheckedProduct(@Param("userId") Integer userId,@Param("productId")Integer productId,@Param("checked") Integer checked);

    int selectCartProductCount(@Param("userId") Integer userId);
}
