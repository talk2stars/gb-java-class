package com.example.jdbc.controller;

import com.example.jdbc.jdbc.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JdbcService jdbcService;

    @GetMapping("/findAll")
    public void findAll() {
        jdbcService.findAll();
    }

    @GetMapping("/add")
    public void add() {
        jdbcService.add();
    }

    @GetMapping("/update")
    public void update() {
        jdbcService.update();
    }
}
