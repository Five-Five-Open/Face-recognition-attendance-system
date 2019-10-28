package com.example.test.service;

import org.springframework.stereotype.Service;
import com.example.test.form.*;
@Service("helloService")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String hello(){
        Hello hello=new Hello();
        return hello.getHello();
    }
}
