package com.example.test.service;

public interface IClassService {

    //主要是还未进入班级时相关页面的Service，在进入某个确定的班级后，将

    //传入的将是用户的ID或是用户名，这个方法会去从数据库匹配相应的数据，并且得到数据库返回的list
    String showData();
    //不传入任何参数，将会自动生成一个随机的班级ID，传入数据库，并且返回创建成功的信息
    String createClass();
    //传入班级的ID，去数据库匹配后返回成功与否
    String joinClass();
    //传入班级的ID，去数据库匹配后返回成功与否
    String deleteClass();
    //学生进入班级
    String stuIntoClass();
    //老师进入班级
    String teaIntoClass();
}
