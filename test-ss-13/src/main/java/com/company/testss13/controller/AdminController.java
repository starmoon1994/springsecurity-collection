package com.company.testss13.controller;


import com.company.testss13.support.RetVO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping("/list")
    public RetVO list() {
        RetVO retVO = new RetVO();
        retVO.setMsg("list");

        return retVO;

    }

    @RequestMapping("/add")
    public RetVO add() {
        RetVO retVO = new RetVO();
        retVO.setMsg("add");

        return retVO;

    }


    @RequestMapping("/getSelfInfo")
    public RetVO getSelfInfo() {
        RetVO retVO = new RetVO();
        retVO.setMsg("getSelfInfo");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        retVO.setData(principal);
        return retVO;

    }


}
