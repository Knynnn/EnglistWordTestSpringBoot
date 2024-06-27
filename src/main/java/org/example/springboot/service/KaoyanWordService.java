package org.example.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.springboot.dao.KaoyanWordDao;
import org.example.springboot.entity.KaoyanWord;
import org.example.springboot.entity.Params;
import org.example.springboot.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Map<String, Object> conductVocabularyTest(List<String> knownWords, List<String> unknownWords, List<String> testWords) {
        Map<String, Object> result = new HashMap<>();

        // 统计各个等级的正确率
        Map<Integer, Integer> correctCounts = new HashMap<>();
        Map<Integer, Integer> totalCounts = new HashMap<>();

        for (String word : testWords) {
            KaoyanWord kaoyanWord = kaoyanWordDao.findByWord(word);
            if (kaoyanWord != null) {
                int level = kaoyanWord.getLevel();
                totalCounts.put(level, totalCounts.getOrDefault(level, 0) + 1);
                if (knownWords.contains(word)) {
                    correctCounts.put(level, correctCounts.getOrDefault(level, 0) + 1);
                }
            }
        }

        // 输出每个等级的 correctCount 和 totalCount
        for (int level = 1; level <= 4; level++) {
            int correctCount = correctCounts.getOrDefault(level, 0);
            int totalCount = totalCounts.getOrDefault(level, 0);
            System.out.println("Level " + level + ": correctCount = " + correctCount + ", totalCount = " + totalCount);
        }

        // 计算总的正确率
        double totalCorrectRate = 0.0;
        for (int level = 1; level <= 4; level++) {
            int correctCount = correctCounts.getOrDefault(level, 0);
            int totalCount = totalCounts.getOrDefault(level, 0);
            if (totalCount > 0) {
                double correctRate = (double) correctCount / totalCount;
                totalCorrectRate += correctRate * level;
            }
        }
        totalCorrectRate /= 4;

        // 估算词汇量
        long totalWords = kaoyanWordDao.count();
        long estimatedVocabularySize = (long) (totalWords * totalCorrectRate);

        result.put("estimatedVocabularySize", estimatedVocabularySize);
        return result;
    }
}
