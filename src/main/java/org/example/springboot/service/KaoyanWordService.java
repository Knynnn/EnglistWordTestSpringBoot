package org.example.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.springboot.dao.KaoyanWordDao;
import org.example.springboot.entity.ChuLiDanCi;
import org.example.springboot.entity.KaoyanWord;
import org.example.springboot.entity.Learning;
import org.example.springboot.entity.Params;
import org.example.springboot.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class KaoyanWordService {

    @Resource
    private KaoyanWordDao kaoyanWordDao;

    public List<KaoyanWord> findAll() {
        return kaoyanWordDao.selectAll();
    }

    public PageInfo<KaoyanWord> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<KaoyanWord> list = kaoyanWordDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(KaoyanWord kaoyanWord) {
        KaoyanWord word = kaoyanWordDao.findByWord(kaoyanWord.getWord());
        if (word != null) {
            throw new CustomException("单词已存在,请勿重复添加");
        }
        kaoyanWordDao.insertSelective(kaoyanWord);
    }

    public void update(KaoyanWord kaoyanWord) {
        kaoyanWordDao.updateByPrimaryKeySelective(kaoyanWord);
    }

    public void delete(Integer id) {
        kaoyanWordDao.deleteByPrimaryKey(id);
    }

    public List<KaoyanWord> getRandomWords(int sampleSize) {
        List<KaoyanWord> words = kaoyanWordDao.selectAll();
        Random rand = new Random();
        return rand.ints(sampleSize, 0, words.size())
                .mapToObj(words::get)
                .collect(Collectors.toList());
    }

    @Transactional
    public int conductVocabularyTest(List<String> knownWords, List<String> unknownWords) throws FileNotFoundException,Exception{
        ChuLiDanCi DC=null;
        try{
            DC=new ChuLiDanCi();
        }catch ( FileNotFoundException e ){
            System.out.printf("文件未找到\n");
        }
        DC.XieDanCi();
        /*
        try{
            System.out.printf("%d\n",DC.half(new String("word")));
        }catch ( Exception e ){
            e.printStackTrace();
        }*/
        Learning ML=new Learning(DC);
        ML.xunLian();
        int result = ML.output(knownWords, unknownWords);
        System.out.println(result);
        return result;
    }
}
