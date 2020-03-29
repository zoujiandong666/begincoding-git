package study.mengduo.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import study.mengduo.common.ServerResponse;
import study.mengduo.mapper.CategoryMapper;
import study.mengduo.pojo.Category;
import study.mengduo.service.ICategoryService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @aothor mengDuo
 * @date 2020/3/22 11:59
 */

@Slf4j
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    //Set<Category> categorySet = new HashSet<>();

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse<String> addCategory(Integer parentId, String categoryName) {
        if (parentId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.creatByErrorMessage("参数不能为空");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        int count = categoryMapper.insert(category);
        if (count>0){
            return ServerResponse.creatByMessageSuccess("创建品类成功");
        }
        return ServerResponse.creatByErrorMessage("创建品类失败");
    }

    @Override
    public ServerResponse<String> updateCategoryName(Integer id, String categoryName) {
        if (id == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.creatByErrorMessage("参数不能为空");
        }
        Category category = new Category();
        category.setId(id);
        category.setName(categoryName);
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if (count>0){
            return ServerResponse.creatByMessageSuccess("更新品类名称成功");
        }
        return ServerResponse.creatByErrorMessage("更新品类名称失败");
    }

    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)){
            log.info("未找到当前分类的子分类");
        }
        return ServerResponse.creatBySuccess(categoryList);
    }

    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        for (Category s: categorySet){
            categoryIdList.add(s.getId());
        }
        return ServerResponse.creatBySuccess(categoryIdList);
    }

    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId){

        Category category = categoryMapper.selectCategoryById(categoryId);
        if (category != null){
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category childCategory: categoryList){
            findChildCategory(categorySet,childCategory.getId());
        }
        return categorySet;
    }
}
