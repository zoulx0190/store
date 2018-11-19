package com.shop.dao;

import com.shop.pojo.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    Order selectByOrderNo(Long orderNo);

    Order selectByUserIdAndOrderNo(@Param("userId")Integer userId,@Param("orderNo")Long orderNo);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}