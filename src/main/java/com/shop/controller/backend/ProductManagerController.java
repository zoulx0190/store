package com.shop.controller.backend;

import com.google.common.collect.Maps;
import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.pojo.Product;
import com.shop.pojo.User;
import com.shop.service.IFileService;
import com.shop.service.IProductService;
import com.shop.service.IUserService;
import com.shop.util.PropertiesUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ Description：商品管理
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/16 18:22
 * @ Modified By：
 */

@Controller
@RequestMapping("/manage/product")
public class ProductManagerController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private  IFileService iFileService;

    @RequestMapping("addProduct.do")
    @ResponseBody
    public ServerResponse addProduct(HttpSession session, Product product){
        User user = (User)session.getAttribute(Const.CURRENT_Admin);

        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，或者该帐号不是管理员");
        }
        return iProductService.saveOrUpdateProduct(product);
    }

    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse<String> setSaleStatus(HttpSession session, Integer productId,Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_Admin);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，或者该帐号不是管理员");
        }
        return iProductService.setSaleStatus(productId,status);
    }

    @RequestMapping("get_product_detail.do")
    @ResponseBody
    public ServerResponse getProductDetail(HttpSession session, Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_Admin);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，或者该帐号不是管理员");
        }
        return iProductService.manageProductDetail(productId);
    }

    @RequestMapping("listProduct.do")
    @ResponseBody
    public ServerResponse listProduct(HttpSession session, @RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
    @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_Admin);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，或者该帐号不是管理员");
        }
        return iProductService.getProductList(pageNum,pageSize);
    }

    @RequestMapping("searchProduct.do")
    @ResponseBody
    public ServerResponse searchProduct(HttpSession session,String productName,Integer productId, @RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
                                      @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_Admin);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，或者该帐号不是管理员");
        }
        return iProductService.searchProduct(productName,productId,pageNum,pageSize);
    }

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false)MultipartFile file
    , HttpServletRequest request){
        User user = (User)session.getAttribute(Const.CURRENT_Admin);
        if(user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，或者该帐号不是管理员");
        }
        String path=request.getSession().getServletContext().getRealPath("upload");
        String targetFileName= iFileService.upload(file,path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+"/"+targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);
        return ServerResponse.createBySuccess(fileMap);
    }
}
