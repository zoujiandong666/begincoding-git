package study.mengduo.service;


import study.mengduo.common.ServerResponse;
import study.mengduo.pojo.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * @aothor mengDuo
 * @date 2020/3/22 11:59
 */



public interface ICategoryService {

    ServerResponse<String> addCategory(Integer parentId, String categoryName);
    ServerResponse<String> updateCategoryName(Integer id, String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
