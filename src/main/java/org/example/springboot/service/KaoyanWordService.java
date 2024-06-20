package org.example.springboot.service;

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

    public List<KaoyanWord> findBySearch(Params params) {
        return kaoyanWordDao.findBySearch(params);
    }
}
