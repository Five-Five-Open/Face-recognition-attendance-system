package com.example.test.service;

public interface ILoginService {
    //这些方法应该是之后去视线并且调用数据库的，在dao模式完成后再行添加

    //形参是学生的账号和密码，返回一个boolean判断是否登陆成功
    Boolean stuLogin();
    //形参是老师的账号和密码，返回一个boolean判断是否登陆成功
    Boolean teacherLogin();
    //形参是账号和密码，匹配后返回boolean判断是否注册成功
    Boolean create();
}
