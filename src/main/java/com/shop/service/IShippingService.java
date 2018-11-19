package com.shop.service;

import com.github.pagehelper.PageInfo;
import com.shop.common.ServerResponse;
import com.shop.pojo.Shipping;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/18 23:43
 * @ Modified By：
 */
public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> delete(Integer userId,Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);


}
