package com.dys.springcloud.service.impl;

import com.dys.springcloud.entity.Word;
import com.dys.springcloud.repository.WordRepository;
import com.dys.springcloud.service.WordService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Resource
    private WordRepository wordRepository;

    @Override
    @CachePut(cacheNames = {"Awords", "Aword"}, keyGenerator = "keyGenerator")
    public Long add(Word word) {

        Date date = new Date();
        word.setCreateTime(date);
        word.setUpdateTime(date);
        this.wordRepository.save(word);

        return word.getId();
    }

    @Override
    @CachePut(cacheNames = {"Awords", "Aword"}, keyGenerator = "keyGenerator")
    public void update(Word word) {
        word.setUpdateTime(new Date());
        this.wordRepository.save(word);
    }

    @Override
    @Cacheable(cacheNames = {"Awords"}, keyGenerator = "keyGenerator")
    public List<Word> list() {

        return this.wordRepository.findAll();

    }

    @Override
    @Cacheable(cacheNames = {"Aword"}, keyGenerator = "keyGenerator")
    public Word findById(Long id) {

        Word word = this.wordRepository.findById(id).get();

        return word;
    }

    @Override
    @CacheEvict(cacheNames = {"Awords", "Aword"}, keyGenerator = "keyGenerator")
    public void deleteById(Long id) {

        this.wordRepository.deleteById(id);

    }
}
