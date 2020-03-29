package study.mengduo.mapper;

import org.apache.ibatis.annotations.Param;
import study.mengduo.pojo.Product;

import java.util.List;

/**
 * @aothor mengDuo
 * @date 2020/3/22 20:13
 */
public interface ProductMapper {
    int insert(Product product);

    int updateByPrimaryKey(Product record);


    int updateByPrimaryKeySelective(Product product);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectList();

    List<Product> selectByNameAndProductId(@Param("productName")String productName, @Param("productId") Integer productId);


}
