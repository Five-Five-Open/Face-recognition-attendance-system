package com.example.test.form;
// 这个类是对班级详细信息的描述，也就是班级中所有学生的信息
public class CDetail {
    private int CDid; //班级详细信息的编号
    private String Cid; //关联的班级ID
    private String Sid; //班级中的学生ID

    public CDetail() {
    }

    public int getCDid() {
        return CDid;
    }

    public void setCDid(int CDid) {
        this.CDid = CDid;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }
}
