package com.manage.entity;

public class Major {
    private int id;
    private String major;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", major='" + major + '\'' +
                '}';
    }
}
