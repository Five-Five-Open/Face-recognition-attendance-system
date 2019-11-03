package com.example.test.springboot;
//查看考勤历史记录的Controller

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("history")
public class HistoryController {

    //实际上，这个方法是学生和老师共用，需要传入班级和学生的id，显示该学生在该班级每一次签到的记录
    @RequestMapping("stuhistory")
    public String stuHistroy(){
        return "stuhistory";
    }

    //教师用方法，查看本班级所有的考勤记录
    @RequestMapping("classhistory")
    private String classHistroy(){
        return "classhistory";
    }

    //教师用方法，查看某次考勤所有学生的出勤情况
    @RequestMapping("atthistory")
    private String attHistroy(){
        return "atthistory";
    }

}
