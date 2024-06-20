package org.example.springboot.dao;

import org.apache.ibatis.annotations.Select;
import org.example.springboot.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserDao extends Mapper<User> {


    //@Select("select * from user")
    List<User> getUser();
}
