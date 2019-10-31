package com.example.test.form;

import java.util.Date;
// 存放考勤信息的类
public class Attendance {
    private String Aid; //本次考勤的ID号
    private String Atype; //本次考勤的考勤类型，分为随机码考勤，gps考勤，人脸识别考勤
    private Date Atime; //本次考勤开始的时间
    private String Cid; //执行本次考勤的班级ID

    public Attendance() {
    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getAtype() {
        return Atype;
    }

    public void setAtype(String atype) {
        Atype = atype;
    }

    public Date getAtime() {
        return Atime;
    }

    public void setAtime(Date atime) {
        Atime = atime;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }
}
