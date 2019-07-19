package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Info04 implements Serializable {

    private String biography;

    private FilmActors actors;

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public FilmActors getActors() {
        return actors;
    }

    public void setActors(FilmActors actors) {
        this.actors = actors;
    }

}
