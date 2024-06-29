package org.example.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.springboot.dao.Cet4WordDao;
import org.example.springboot.entity.Cet4Word;
import org.example.springboot.entity.Params;
import org.example.springboot.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cet4WordService {

    @Resource
    private Cet4WordDao cet4WordDao;

    public List<Cet4Word> findAll() {
        return cet4WordDao.selectAll();
    }

    public PageInfo<Cet4Word> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Cet4Word> list = cet4WordDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Cet4Word cet4Word) {
        Cet4Word word = cet4WordDao.findByWord(cet4Word.getWord());
        if (word != null) {
            throw new CustomException("单词已存在,请勿重复添加");
        }
        cet4WordDao.insertSelective(cet4Word);
    }

    public void update(Cet4Word cet4Word) {
        cet4WordDao.updateByPrimaryKeySelective(cet4Word);
    }

    public void delete(Integer id) {
        cet4WordDao.deleteByPrimaryKey(id);
    }
}
