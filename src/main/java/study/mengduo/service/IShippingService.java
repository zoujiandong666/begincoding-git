package study.mengduo.service;

import com.github.pagehelper.PageInfo;
import study.mengduo.common.ServerResponse;
import study.mengduo.pojo.Shipping;


/**
 * Created by geely
 */
public interface IShippingService {

    ServerResponse add(Integer userId, Shipping shipping);
    ServerResponse<String> del(Integer userId, Integer shippingId);
    ServerResponse update(Integer userId, Shipping shipping);
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

}
