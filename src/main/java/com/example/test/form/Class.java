package com.example.test.form;

public class Class {
    private String Cid; //班级的ID，即随机产生的入班号
    private String Cname; //班级的名称
    private String Tid; //创建班级的老师ID，也就是班级的所有者

    public Class() {
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }
}
