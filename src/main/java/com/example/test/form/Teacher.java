package com.example.test.form;

public class Teacher {
    private String Tid; //教师的ID
    private String Tpassword; //教师的密码
    private String Tname; //教师的昵称

    public Teacher() {
    }

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public String getTpassword() {
        return Tpassword;
    }

    public void setTpassword(String tpassword) {
        Tpassword = tpassword;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }
}
