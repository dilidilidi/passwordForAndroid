package com.sunbin.password.entity;

/**
 * Created by dilidilidi on 2015/3/22.
 * Email:93146139@qq.com
 */
public class Password {
    private int id;
    private String password;
    private String salt;
    private String comment;

    /**
     * 构造函数
     *
     * @param id       ID
     * @param password 密码
     * @param salt     加密因子
     * @param comment  备注
     */
    public Password(int id, String password, String salt, String comment) {
        this.id = id;
        this.password = password;
        this.salt = salt;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
                ", salt='" + salt + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}