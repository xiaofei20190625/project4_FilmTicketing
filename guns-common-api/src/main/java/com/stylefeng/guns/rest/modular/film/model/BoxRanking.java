package com.stylefeng.guns.rest.modular.film.model;

import java.io.Serializable;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/15 Time 21:53
 */
public class BoxRanking implements Serializable {
    private Integer filmId;

    private String imgAddress;

    private String filmName;

    private Integer boxNum;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
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

    public Integer getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Integer boxNum) {
        this.boxNum = boxNum;
    }

    @Override
    public String toString() {
        return "BoxRanking{" +
                "filmId='" + filmId + '\'' +
                ", imgAddress='" + imgAddress + '\'' +
                ", filmName='" + filmName + '\'' +
                ", boxNum=" + boxNum +
                '}';
    }
}
