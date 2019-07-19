package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class FilmResponseVO implements Serializable {
    private Integer filmId;

    private String filmType;

    private String filmName;

    private String filmScore;

    private String imgAddress;
}
