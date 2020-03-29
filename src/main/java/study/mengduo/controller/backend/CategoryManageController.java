package study.mengduo.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.mengduo.common.Const;
import study.mengduo.common.ResponseCode;
import study.mengduo.common.ServerResponse;
import study.mengduo.pojo.Category;
import study.mengduo.pojo.User;
import study.mengduo.service.ICategoryService;
import study.mengduo.service.IUserService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @aothor mengDuo
 * @date 2020/3/22 11:28
 */

@RestController
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping("addCategory")
    public ServerResponse addCategory(HttpSession httpSession, @RequestParam(value = "parentId",defaultValue = "0") int parentId, String categoryName){

        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登录");
        }

        if (iUserService.checkIsAdmin(user).isSuccess()){
            //增加类目
            return iCategoryService.addCategory(parentId, categoryName);
        }
        return ServerResponse.creatByErrorMessage("非法操作，不是管理员");
    }


    @PostMapping("updateCategoryName")
    public ServerResponse updateCategoryName(HttpSession httpSession, int id, String categoryName){
        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登录");
        }
        if (iUserService.checkIsAdmin(user).isSuccess()){
            return iCategoryService.updateCategoryName(id, categoryName);
        }
        return ServerResponse.creatByErrorMessage("非法操作，不是管理员");
    }


    /**
     * 根据传入的类目ID查询下面的子类目信息，不递归
     * @param httpSession
     * @param categoryId
     * @return
     */
    @GetMapping("getChildCategory")
    public ServerResponse<List<Category>> getChildrenParallelCategory(HttpSession httpSession,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登录");
        }
        if (iUserService.checkIsAdmin(user).isSuccess()) {
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }
        return ServerResponse.creatByErrorMessage("无权限操作,需要管理员权限");
    }


    @GetMapping("getDeepCategory")
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession httpSession,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId ){
        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登录");
        }
        if (iUserService.checkIsAdmin(user).isSuccess()) {
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }
        return ServerResponse.creatByErrorMessage("无权限操作,需要管理员权限");
    }



}
