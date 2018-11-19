package com.shop.service;

import com.github.pagehelper.PageInfo;
import com.shop.common.ServerResponse;
import com.shop.pojo.Product;
import com.shop.vo.ProductDetailVo;

import java.util.List;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/16 22:02
 * @ Modified By：
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId,Integer status);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(Integer pageNum, Integer pageSize);

    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, Integer pageNum, Integer pageSize);

    ServerResponse<PageInfo> getProductByRequirements(String eyword,Integer categoryId,Integer pageNum,Integer pageSize,String orderBy);
}
