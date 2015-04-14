package com.sunbin.password.entity;

/**
 * Created by dilidilidi on 2015/3/22.
 * Email:93146139@qq.com
 * 密码记录类
 */
public class Password {
    private int id;
    private String cipherText;
    private String salt;
    private String iv;
    private String remark;

    /**
     * 密码对象
     *
     * @param id         ID
     * @param cipherText 密文
     * @param salt       盐值
     * @param iv         加密因子
     * @param remark     备注
     */
    public Password(int id, String cipherText, String salt, String iv, String remark) {
        this.id = id;
        this.cipherText = cipherText;
        this.salt = salt;
        this.iv = iv;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}