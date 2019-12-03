package com.example.helloworld.SQLiteOpenHelper;

import android.content.Intent;

public class Student {
    private Integer id;
    private String name1;
    private String phone;
    private String num;
    private String name2;

    public Student(Integer id, String name1, String phone, String num, String name2) {
        this.id = id;
        this.name1 = name1;
        this.phone = phone;
        this.num = num;
        this.name2 = name2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Override
    public String toString() {
        return

                " name1：'" + name1 + '\'' +
                ", phone：" + phone +
                ", num：" + num + '\'' +
                ", name2：" + name2 + '\'' +
                '}';
    }
}