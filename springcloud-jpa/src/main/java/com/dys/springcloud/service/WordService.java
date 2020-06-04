package com.dys.springcloud.service;

import com.dys.springcloud.entity.Word;

import java.util.List;

public interface WordService {
    Long add(Word word);

    void update(Word word);

    List<Word> list();

    Word findById(Long id);
}
