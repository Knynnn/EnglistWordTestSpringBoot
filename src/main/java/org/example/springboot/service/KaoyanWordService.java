package org.example.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.springboot.dao.KaoyanWordDao;
import org.example.springboot.entity.KaoyanWord;
import org.example.springboot.entity.Params;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaoyanWordService {

    @Resource
    private KaoyanWordDao kaoyanWordDao;

    public List<KaoyanWord> finnAll() {
        return kaoyanWordDao.selectAll();
    }

    public PageInfo<KaoyanWord> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        //接下来的查询会自动按照当前开启的分页设置来查询
        List<KaoyanWord> list = kaoyanWordDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(KaoyanWord kaoyanWord) {
        kaoyanWordDao.insertSelective(kaoyanWord);
    }

    public void update(KaoyanWord kaoyanWord) {
        kaoyanWordDao.updateByPrimaryKeySelective(kaoyanWord);
    }

    public void delete(Integer id) {
        kaoyanWordDao.deleteByPrimaryKey(id);
    }
}
