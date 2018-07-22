package com.company.testspringsecurity1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("/")
    public String getIndex() {

        return "index";
    }


    @GetMapping("/ss")
    public String getSS() {

        return "ssss";
    }

    @RequestMapping ("/authentication/require")
    public String getS1S() {

        return "/authentication/require";
    }
}
