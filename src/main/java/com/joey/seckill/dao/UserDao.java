package com.joey.seckill.dao;

import com.joey.seckill.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from seckill_user where id =#{id}")
    public User getById(@Param("id") Long id);
}
