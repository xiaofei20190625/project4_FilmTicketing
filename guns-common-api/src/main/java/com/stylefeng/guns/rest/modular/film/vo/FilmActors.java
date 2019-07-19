package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class FilmActors implements Serializable {

    private Director director;

    private List<Actor> actors;

    public FilmActors() {
    }

    public FilmActors(Director director, List<Actor> actors) {
        this.director = director;
        this.actors = actors;
    }
}
