package study.mengduo.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.mengduo.common.Const;
import study.mengduo.common.ResponseCode;
import study.mengduo.common.ServerResponse;
import study.mengduo.pojo.Product;
import study.mengduo.pojo.User;
import study.mengduo.service.IProductService;
import study.mengduo.service.IUserService;

import javax.servlet.http.HttpSession;

/**
 * @aothor mengDuo
 * @date 2020/3/22 20:11
 */

@RestController
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IUserService iUserService;

    @PostMapping("addOrUpdateProduct")
    public ServerResponse addOrUpdateProduct(HttpSession session, Product product){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登录");
        }

        if (iUserService.checkIsAdmin(user).isSuccess()){
            return iProductService.addOrUpdateProduct(product);
        }
        return ServerResponse.creatByErrorMessage("非法操作，不是管理员");
    }


    @PostMapping("setSaleStatus")
    public ServerResponse<String> setSaleStatus(HttpSession session,Integer productId,Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登录");
        }
        if(iUserService.checkIsAdmin(user).isSuccess()){
            return iProductService.setSaleStatus(productId,status);
        }else{
            return ServerResponse.creatByErrorMessage("无权限操作");
        }
    }


    @GetMapping("getItemDetail")
    public ServerResponse getItemDetail(HttpSession session, Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");

        }
        if(iUserService.checkIsAdmin(user).isSuccess()){
            //填充业务
            return iProductService.manageProductDetail(productId);

        }else{
            return ServerResponse.creatByErrorMessage("无权限操作");
        }
    }


    @GetMapping("getItemList")
    public ServerResponse getItemList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");

        }
        if(iUserService.checkIsAdmin(user).isSuccess()){
            //填充业务
            return iProductService.getProductList(pageNum,pageSize);
        }else{
            return ServerResponse.creatByErrorMessage("无权限操作");
        }
    }

    @GetMapping("productSearch")
    public ServerResponse productSearch(HttpSession session,String productName,Integer productId, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.creatByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");

        }
        if(iUserService.checkIsAdmin(user).isSuccess()){
            //填充业务
            return iProductService.searchProduct(productName,productId,pageNum,pageSize);
        }else{
            return ServerResponse.creatByErrorMessage("无权限操作");
        }
    }


}
