package com.sunbin.password.entity;

/**
 * Created by dilidilidi on 2015/3/22.
 * Email:93146139@qq.com
 */
public class Password {
    private int id;
    private String password;
    private String comment;

    public Password(int id, String password, String comment) {
        this.id = id;
        this.password = password;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}