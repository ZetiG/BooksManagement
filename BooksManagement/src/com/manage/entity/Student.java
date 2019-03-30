package com.manage.entity;

/**
 * 学生实体类
 */
public class Student {

    private int stuUid;
    private String stuNo;
    private String stuName;
    private String stuMajor;
    private String stuSex;
    private String stuAge;
    private String stuPhone;
    private int stuMotto;

    public int getStuUid() {
        return stuUid;
    }

    public void setStuUid(int stuUid) {
        this.stuUid = stuUid;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public int getStuMotto() {
        return stuMotto;
    }

    public void setStuMotto(int stuMotto) {
        this.stuMotto = stuMotto;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuUid=" + stuUid +
                ", stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuMajor='" + stuMajor + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuAge='" + stuAge + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuMotto=" + stuMotto +
                '}';
    }
}
