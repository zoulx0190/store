package com.shop.service;

import com.shop.common.ServerResponse;

import java.util.Map;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/19 15:53
 * @ Modified By：
 */
public interface IOrderService {
     ServerResponse pay(Long orderNo, Integer userId, String path) ;

     ServerResponse aliCallback(Map<String, String> paramMap);

     ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

     ServerResponse createOrder(Integer id, Integer shippingId);
}
