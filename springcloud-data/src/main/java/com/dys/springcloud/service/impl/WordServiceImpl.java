package com.dys.springcloud.service.impl;

import com.dys.springcloud.entity.Word;
import com.dys.springcloud.repository.WordRepository;
import com.dys.springcloud.service.WordService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Resource
    private WordRepository wordRepository;

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"Awords"}, allEntries = true),
                    @CacheEvict(cacheNames = {"Awords"}, allEntries = true)
            }
    )
    public Word add(Word word) {

        Date date = new Date();
        word.setCreateTime(date);
        word.setUpdateTime(date);
        word = this.wordRepository.save(word);

        return word;
    }

    @Override
    @Caching(
            put = {
                    @CachePut(cacheNames = {"Aword"}, key = "#root.args[0].getId()")
            },
            evict = {
                    @CacheEvict(cacheNames = {"Awords"}, allEntries = true),
                    @CacheEvict(cacheNames = {"Awords"}, allEntries = true)
            }
    )
    public Word update(Word word) {
        word.setUpdateTime(new Date());
        word = this.wordRepository.save(word);

        return word;
    }

    @Override
    @Cacheable(cacheNames = {"Awords"}, key = "#root.methodName")
    public List<Word> list() {

        return this.wordRepository.findAll();

    }

    @Override
    @Cacheable(cacheNames = {"Aword"}, key = "#root.args[0]")
    public Word findById(Long id) {

        Word word = this.wordRepository.findById(id).get();

        return word;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"Awords"}, allEntries = true),
                    @CacheEvict(cacheNames = {"Awords"}, allEntries = true),
                    @CacheEvict(cacheNames = {"Aword"}, key = "#root.args[0]")
            }
    )
    public void deleteById(Long id) {

        this.wordRepository.deleteById(id);

    }

    @Override
    @Cacheable(cacheNames = {"Awords"}, key = "#root.methodName + ':' + #root.args[0]")
    public List<Word> findByUserId(Long userId) {
        List<Word> words = this.wordRepository.findByUserId(userId);
        return words;
    }
}
