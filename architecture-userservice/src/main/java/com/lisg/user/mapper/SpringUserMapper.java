package com.lisg.user.mapper;


import com.lisg.user.entity.SpringUser;

public interface SpringUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(SpringUser record);

    int insertSelective(SpringUser record);

    SpringUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SpringUser record);

    int updateByPrimaryKey(SpringUser record);
}