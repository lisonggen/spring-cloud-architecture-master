package com.lisg.order.mapper;

import com.lisg.order.SpringOrder;

public interface SpringOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(SpringOrder record);

    int insertSelective(SpringOrder record);

    SpringOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(SpringOrder record);

    int updateByPrimaryKey(SpringOrder record);
}