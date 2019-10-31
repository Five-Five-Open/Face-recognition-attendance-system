package com.example.test.form;

public class Student {
    private String Sid; //学生的id
    private String Spassword; //学生的密码
    private String Sname; //学生的昵称

    public Student() {
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSpassword() {
        return Spassword;
    }

    public void setSpassword(String spassword) {
        Spassword = spassword;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

}
