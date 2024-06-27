package org.example.springboot.dao;

import org.apache.ibatis.annotations.Select;
import org.example.springboot.entity.KaoyanWord;
import org.example.springboot.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
public interface UserDao extends Mapper<User> {

    @Select("select * from user where id = #{id} limit 1")
    User findById(Integer id);

    @Select("select * from user where id = #{id} and password = #{password} limit 1")
    User findByIdAndPassword(Integer id, String password);
}
