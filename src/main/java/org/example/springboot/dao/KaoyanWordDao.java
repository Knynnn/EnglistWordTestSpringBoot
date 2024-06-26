package org.example.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.springboot.entity.KaoyanWord;
import org.example.springboot.entity.Params;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface KaoyanWordDao extends Mapper<KaoyanWord> {

    List<KaoyanWord> findBySearch(@Param("params") Params params);

    @Select("select * from kaoyan where word = #{word} limit 1")
    KaoyanWord findByWord(String word);

    @Select("select count(*) from kaoyan")
    long count();
}
