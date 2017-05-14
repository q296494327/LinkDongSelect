package com.explam.linkdongdemo;

import java.io.Serializable;

/**
 * User: xiemiao
 * Date: 2017-05-14
 * Time: 11:05
 * Desc:
 */
public class Department implements Serializable {
    private int uid;
    private String name;
    private String category;
    private String introduce;

    public Department(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
