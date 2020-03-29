package study.mengduo.service;

import com.github.pagehelper.PageInfo;
import study.mengduo.common.ServerResponse;
import study.mengduo.pojo.Product;
import study.mengduo.vo.ProductDetailVo;

/**
 * @aothor mengDuo
 * @date 2020/3/22 20:13
 */
public interface IProductService {
    ServerResponse<String> addOrUpdateProduct(Product product);
    ServerResponse<String> setSaleStatus(Integer productId,Integer status);
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);
    ServerResponse<PageInfo> searchProduct(String productName,Integer productId,int pageNum,int pageSize);
}
