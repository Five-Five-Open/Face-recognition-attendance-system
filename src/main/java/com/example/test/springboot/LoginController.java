package com.example.test.springboot;
//主要负责登录和注册相关的模块
//登录成功后，对班级的操作将转移到ClassController内

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {
}
