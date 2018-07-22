package com.company.testss12.controller;

import com.company.testss12.support.RetVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/delete")
    public RetVO list(){
        RetVO retVO = new RetVO();
        retVO.setMsg("delete");

        return retVO;

    }

}
