package org.example.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@Component
@DubboService(version = "1.0.0",interfaceClass = TestService.class)
public class TestServiceImpl implements TestService {
    @Override
    public String sel() {
       return "我是dubbo提供者1";
    }

    @Override
    public String pay() {
        return "我是dubbo提供者2";
    }
}
