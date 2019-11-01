package com.example.test.springboot;

import com.example.test.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//是对班级操作的controller，包括显示班级，创建班级，加入班级和解散班级
//与ATTController和HistroyController相关联

@Controller
@RequestMapping("class")
public class ClassController {
    @Autowired
    IClassService classService;
    //刷新展示学生进入班级前的页面
    @RequestMapping("stushow")
    public String showStuData(){
        return "stuMain";
    }

    //刷新展示老师进入班级前的页面
    @RequestMapping("teachershow")
    public String showTeaData(){
        return "teaMain";
    }

    //创建班级，创建后刷新显示
    @RequestMapping("createClass")
    public String cteateClass(){
        return "redirect:/teaMain";
    }

    //加入班级，加入后刷新显示
    @RequestMapping("joinClass")
    public String joinClass(){
        return "redirect:/stuMain";
    }

    //删除班级，删除后刷新显示
    @RequestMapping("deleteClass")
    public String deleteClass(){
        return "redirect:/teaMain";
    }

    //进入某个详细的班级，并且显示班级里所有的成员信息
    @RequestMapping("stuclass")
    private String stuIntoClass(){
        return "stuclass";
    }

    @RequestMapping("teaclass")
    private String teaIntoClass(){
        return "teaclass";
    }
}
