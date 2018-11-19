package com.shop.controller.protal;

import com.github.pagehelper.PageInfo;
import com.shop.common.ServerResponse;
import com.shop.dao.ProductMapper;
import com.shop.service.IProductService;
import com.shop.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/18 17:26
 * @ Modified By：
 */

@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> productDetail(@RequestParam(value = "productId")Integer productId){
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> productList(@RequestParam(value = "keyword",required = false)String keyword,
@RequestParam(value = "categoryId",required = false)Integer categoryId,
@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
@RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
@RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return iProductService.getProductByRequirements(keyword,categoryId,pageNum,pageSize,orderBy);
    }

}
