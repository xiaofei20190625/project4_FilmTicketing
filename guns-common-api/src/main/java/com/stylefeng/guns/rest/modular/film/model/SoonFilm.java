package com.stylefeng.guns.rest.modular.film.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/15 Time 21:49
 */
public class SoonFilm implements Serializable {
    private Integer filmId;

    private Integer filmType;

    private String imgAddress;

    private String filmName;

    private Integer expectNum;

    private Date showTime;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getFilmType() {
        return filmType;
    }

    public void setFilmType(Integer filmType) {
        this.filmType = filmType;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Integer getExpectNum() {
        return expectNum;
    }

    public void setExpectNum(Integer expectNum) {
        this.expectNum = expectNum;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "SoonFilm{" +
                "filmId='" + filmId + '\'' +
                ", filmType=" + filmType +
                ", imgAddress='" + imgAddress + '\'' +
                ", filmName='" + filmName + '\'' +
                ", expectNum=" + expectNum +
                ", showTime=" + showTime +
                '}';
    }
}
