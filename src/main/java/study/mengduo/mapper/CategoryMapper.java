package study.mengduo.mapper;

import study.mengduo.pojo.Category;

import java.util.List;

/**
 * @aothor mengDuo
 * @date 2020/3/22 12:00
 */
public interface CategoryMapper {
    int insert(Category record);
    int updateByPrimaryKeySelective(Category record);
    List<Category> selectCategoryChildrenByParentId(Integer parentId);
    Category selectCategoryById(Integer categoryId);
}
