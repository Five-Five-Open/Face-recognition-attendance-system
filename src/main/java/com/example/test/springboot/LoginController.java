package com.example.test.springboot;
//主要负责登录和注册相关的模块
//登录成功后，对班级的操作将转移到ClassController内

import com.example.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    //@Autowired
    //private ILoginService loginService;
    //学生登录的Controller
    @RequestMapping("stu")
    public String stuLogin(){
        return "redirect:/class/stuMain";
    }
    //教师登录的Controller
    @RequestMapping("teacher")
    public String teacherLogin(){
        return "redirect:/class/teacherMain";
    }
    //两者共用同一个注册页面
    @RequestMapping("create")
    public String create(){
        return "create";
    }
}
