package study.mengduo.service;

import study.mengduo.common.ServerResponse;
import study.mengduo.vo.CartVo;

/**
 * @aothor mengDuo
 * @date 2020/3/26 21:26
 */
public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);

    ServerResponse<CartVo> selectOrUnSelect (Integer userId,Integer productId,Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);

}
