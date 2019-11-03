package com.example.test.service;

import com.example.test.dao.HelloDao;
import com.example.test.dao.IHelloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.test.form.*;

import java.util.List;

@Service("helloService")
/*public class HelloServiceImpl implements IHelloService {
    @Override
    public String hello(){
        Hello hello=new Hello();
        return hello.getHello();
    }
}*/


public class HelloServiceImpl implements IHelloService {

    @Autowired
    private IHelloDao helloDao;
    @Override
    public List<Test> hello(){
        List<Test> testList=helloDao.list();
        return testList;
    }
}
