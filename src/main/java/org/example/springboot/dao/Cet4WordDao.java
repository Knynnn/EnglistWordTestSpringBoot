package org.example.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.springboot.entity.Cet4Word;
import org.example.springboot.entity.Params;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface Cet4WordDao extends Mapper<Cet4Word> {

    List<Cet4Word> findBySearch(@Param("params") Params params);

    @Select("select * from Cet4 where word = #{word} limit 1")
    Cet4Word findByWord(String word);

    @Select("select count(*) from Cet4")
    long count();
}