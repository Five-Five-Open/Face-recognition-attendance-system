package com.example.test.form;
//记录考勤详细信息的类
public class ADetail {
    private int ADid; //考勤详细信息的编号
    private String Aid; //这条信息所属的考勤ID
    private String Sid; //拥有这条考勤信息的学生ID
    private String Situation; //这位学生的考勤状态

    public ADetail() {
    }

    public int getADid() {
        return ADid;
    }

    public void setADid(int ADid) {
        this.ADid = ADid;
    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSituation() {
        return Situation;
    }

    public void setSituation(String situation) {
        Situation = situation;
    }
}
