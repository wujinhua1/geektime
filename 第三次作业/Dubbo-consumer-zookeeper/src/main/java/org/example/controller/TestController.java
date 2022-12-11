package org.example.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.service.TestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class TestController{
    @DubboReference(version = "1.0.0")
    TestService testService;

    @GetMapping("test1")
    public String testDubbo(){
        return testService.sel();
    }

    @GetMapping("test2")
    public String demo(){
        return testService.pay();
    }

}
