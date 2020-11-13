package com.dys.springcloud.controller;

import com.dys.springcloud.entity.Word;
import com.dys.springcloud.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = {"/word"})
public class WordController {

    @Resource
    private WordService wordService;

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String add() {
        return "add";
    }

    @RequestMapping(value = {"/add"})
    public String add(Word word) {

        wordService.add(word);
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "list";
    }

    @RequestMapping(value = {"/delete"})
    public String delete(Word word) {

        wordService.deleteById(word.getId());
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "list";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String edit(Word word, Model model) {
        word = wordService.findById(word.getId());
        model.addAttribute("word", word);
        return "edit";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public String edit(Word word) {

        wordService.update(word);

        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "list";
    }

    @RequestMapping(value = {"/list"})
    public String list(Model model) {
        List<Word> words = this.wordService.list();
        model.addAttribute("words", words);
        return "list";
    }
}
