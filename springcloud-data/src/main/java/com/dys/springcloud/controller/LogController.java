package com.dys.springcloud.controller;

import com.dys.springcloud.annotation.OperationLog;
import com.dys.springcloud.entity.Word;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/log"})
public class LogController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = {"/logGet"})
    @ResponseBody
    @OperationLog(value = "这是获取id为#{#id}的单词信息")
    public Word logGet(@RequestParam(name = "id") Long id) {
        Word word = new Word();
        word.setId(id);
        word.setWordName("good");
        return word;
    }

    @RequestMapping(value = {"/logPostJson"})
    @ResponseBody
    @OperationLog(value = "保存单词信息#{#word}")
    public Word logPostJson(@RequestBody Word word) {
        System.out.println(word);
        return word;
    }

    @RequestMapping(value = {"/logPostForm"})
    @ResponseBody
    @OperationLog(value = "保存单词信息#{#word}")
    public Word logPostForm(Word word) {
        System.out.println(word);
        return word;
    }

    @RequestMapping(value = {"/logPostString"})
    @ResponseBody
    @OperationLog(value = "保存单词信息#{#word}}")
    public Word logPostString(String word) throws Exception {

        int a = 10;
        int b = 0;
        int c = a / b;

        Word word1 = this.objectMapper.readValue(word, Word.class);

        System.out.println(word1);

        return word1;
    }

    @RequestMapping(value = {"/logGetRequest"})
    @ResponseBody
    @OperationLog(value = "保存单词信息#{#id}")
    public Word logGetRequest(HttpServletRequest request) throws Exception {

        Long id = Long.valueOf(request.getParameter("id"));
        Word word = new Word();
        word.setId(id);
        word.setWordName("good");
        return word;
    }
}
