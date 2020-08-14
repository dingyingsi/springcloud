package com.dys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/add")
    public String add() {
        return "add";
    }

    @RequestMapping(value = "/update")
    public String update() {
        return "update";
    }

    @RequestMapping(value = "/delete")
    public String delete() {
        return "delete";
    }

    @RequestMapping(value = "/create")
    public String create() {
        return "create";
    }

}
