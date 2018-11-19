package com.shop.dao;

import com.shop.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByShippingIdUserId(@Param("userId")Integer userId,@Param("shippingId")Integer shippingId);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    Shipping selectByShippingIdUserId(@Param("userId")Integer userId,@Param("shippingId")Integer shippingId);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int updateByShipping(Shipping shipping);

    List<Shipping> selectByUserId(Integer userId);


}