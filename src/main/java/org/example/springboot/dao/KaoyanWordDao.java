package org.example.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.example.springboot.entity.KaoyanWord;
import org.example.springboot.entity.Params;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface KaoyanWordDao extends Mapper<KaoyanWord> {

    List<KaoyanWord> findBySearch(@Param("params") Params params);
}
