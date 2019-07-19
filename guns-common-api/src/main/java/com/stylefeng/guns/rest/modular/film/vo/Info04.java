package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Info04 implements Serializable {

    private String biography;

    private FilmActors actors;

    private ImgVO imgVO;

    private String filmId;

    public Info04() {
    }

    public Info04(String biography, FilmActors actors, ImgVO imgVO, String filmId) {
        this.biography = biography;
        this.actors = actors;
        this.imgVO = imgVO;
        this.filmId = filmId;
    }
}
