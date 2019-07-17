package com.stylefeng.guns.rest.modular.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xiaofei
 * @since 2019-07-17
 */
public class UserInfo implements Serializable {


    private Integer uuid;
    /**
     * 用户账号
     */

    private String username;

    /**
     * 用户昵称
     */

    private String nickname;
    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机号
     */

    private String phone;
    /**
     * 用户性别 0-男，1-女
     */

    private Integer sex;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 生活状态 0-单身，1-热恋中，2-已婚，3-为人父母
     */

    private Integer lifeState;
    /**
     * 个人介绍
     */
    private String biography;

    /**
     * 用户住址
     */
    private String address;
    /**
     * 头像URL
     */

    private String headAddress;


    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 修改时间
     */

    private Date updateTime;


    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getLifeState() {
        return lifeState;
    }

    public void setLifeState(Integer lifeState) {
        this.lifeState = lifeState;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadAddress() {
        return headAddress;
    }

    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
