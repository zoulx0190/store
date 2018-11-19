package com.shop.service;

import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.vo.CartVo;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/18 21:39
 * @ Modified By：
 */
public interface ICartService {

    ServerResponse add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list (Integer userId);

    ServerResponse<CartVo> selectOrUnSelect (Integer userId,Integer productId,Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
