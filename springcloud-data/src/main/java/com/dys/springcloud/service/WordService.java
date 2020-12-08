package com.dys.springcloud.service;

import com.dys.springcloud.entity.Word;

import java.util.List;

public interface WordService {

    Word add(Word word);

    Word update(Word word);

    List<Word> list();

    Word findById(Long id);

    void deleteById(Long id);

    List<Word> findByUserId(Long userId);
}
