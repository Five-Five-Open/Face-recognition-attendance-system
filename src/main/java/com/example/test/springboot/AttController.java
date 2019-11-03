package com.example.test.springboot;
//执行考勤操作

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Attend")
public class AttController {
    //教师主动发送考勤通知，具体socket在Service中实现
    @RequestMapping("sendAtt")
    public String sendAtt(){
        return "sent"; //这将是一个提示页面，点击后跳转回班级主界面
    }
    //学生响应考勤，这里的X不是真正的字符串，而是根据Service返回的考勤类型而变动，前往不同的考勤页面
    @RequestMapping("joinAtt")
    public String joinAtt(){
        return "x";
    }

    @RequestMapping("endAtt")
    //学生参加完考勤后，传入后台具体的考勤数据
    public String endAtt(){
        return "end"; //这将是一个提示页面，点击后跳转回班级主界面
    }

}
