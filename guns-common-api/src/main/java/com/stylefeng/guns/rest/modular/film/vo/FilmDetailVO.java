package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class FilmDetailVO implements Serializable {
    private String filmName;

    private String filmEnName;

    private String imgAddress;

    private String score;

    private String scoreNum;

    private String totalBox;

    private String info01;

    private String info02;

    private String info03;

    private Info04 info04;

    private Integer filmId;

    public FilmDetailVO() {
    }

    public FilmDetailVO(String filmName, String filmEnName, String imgAddress, String score, String scoreNum, String totalBox, String info01, String info02, String info03, Info04 info04, Integer filmId) {
        this.filmName = filmName;
        this.filmEnName = filmEnName;
        this.imgAddress = imgAddress;
        this.score = score;
        this.scoreNum = scoreNum;
        this.totalBox = totalBox;
        this.info01 = info01;
        this.info02 = info02;
        this.info03 = info03;
        this.info04 = info04;
        this.filmId = filmId;
    }
}
