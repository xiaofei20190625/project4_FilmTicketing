package com.stylefeng.guns.rest.modular.film.model;

import java.io.Serializable;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/15 Time 21:55
 */
public class Top100 implements Serializable {
    private Integer filmId;

    private String imgAddress;

    private String filmName;

    private String score;

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Top100{" +
                "filmId='" + filmId + '\'' +
                ", imgAddress='" + imgAddress + '\'' +
                ", filmName='" + filmName + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
