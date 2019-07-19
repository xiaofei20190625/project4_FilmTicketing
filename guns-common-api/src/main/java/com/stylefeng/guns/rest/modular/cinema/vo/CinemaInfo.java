package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/17 21:30
 * @Description TODO
 */
@Data
public class CinemaInfo implements Serializable {
    private  Integer cinemaId;
    private  String imgUrl = "cinema6.jpg";
    private  String cinemaName;
    private  String cinemaAdress;
    private String cinemaPhone;

    public CinemaInfo() {
    }

    public CinemaInfo(Integer cinemaId, String imgUrl, String cinemaName, String cinemaAdress, String cinemaPhone) {
        this.cinemaId = cinemaId;
        this.imgUrl = imgUrl;
        this.cinemaName = cinemaName;
        this.cinemaAdress = cinemaAdress;
        this.cinemaPhone = cinemaPhone;
    }
/*
    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaAdress() {
        return cinemaAdress;
    }

    public void setCinemaAdress(String cinemaAdress) {
        this.cinemaAdress = cinemaAdress;
    }

    public String getCinemaPhone() {
        return cinemaPhone;
    }

    public void setCinemaPhone(String cinemaPhone) {
        this.cinemaPhone = cinemaPhone;
    }*/
}
