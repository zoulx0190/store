package com.shop.controller.backend;

import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.pojo.Category;
import com.shop.pojo.User;
import com.shop.service.ICategoryService;
import com.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Description：后台分类管理
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/14 13:10
 * @ Modified By：
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManagerController {

    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse<String> addCategory(HttpSession session, String categoryName, @RequestParam(value="parentId", defaultValue="0")Integer parentId){
        User user = (User) session.getAttribute(Const.CURRENT_Admin);
            if(user == null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
            }
            if(iUserService.checkAdminRole(user).isSuccess()){
                return iCategoryService.addCategory(categoryName,parentId);
            }else {
                return ServerResponse.createByErrorMessage("用户不是管理员，没有权限操作");
            }
    }

    @RequestMapping("update_category.do")
    @ResponseBody
    public ServerResponse<String> updateCategory(HttpSession session, Integer categoryId, String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_Admin);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iCategoryService.updateCategoryName(categoryId,categoryName);
        }else {
            return ServerResponse.createByErrorMessage("用户不是管理员，没有权限操作");
        }
    }
/**
 * create by    : zlx
 * create time  : 0:46 2018/11/15
 * description  :查询分类下的子分类
 *
 * @Param: null
 * @return
 */
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse<List<Category>> getChildrenParallelCategory(HttpSession session, @RequestParam(value="categoryId",defaultValue = "0")Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_Admin);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("用户不是管理员，没有权限操作");
        }
    }
    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse<List<Integer>> selectCategoryAndChildren(HttpSession session,Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_Admin);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("用户不是管理员，没有权限操作");
        }
    }
}
