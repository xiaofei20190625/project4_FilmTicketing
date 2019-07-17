package com.stylefeng.guns.rest.modular.film.model;

import java.io.Serializable;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/15 Time 21:44
 */
public class HotFilm implements Serializable {
    private Integer filmId;

    private Integer filmType;

    private String imgAddress;

    private String filmName;

    private String filmScore;

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

    public String getFilmScore() {
        return filmScore;
    }

    public void setFilmScore(String filmScore) {
        this.filmScore = filmScore;
    }

    @Override
    public String toString() {
        return "HotFilm{" +
                "filmId=" + filmId +
                ", filmType=" + filmType +
                ", imgAddress='" + imgAddress + '\'' +
                ", filmName='" + filmName + '\'' +
                ", filmScore='" + filmScore + '\'' +
                '}';
    }
}
