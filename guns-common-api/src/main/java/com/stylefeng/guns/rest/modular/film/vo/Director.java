package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Director implements Serializable {

    private String imgAddress;

    private String directorName;

    public Director() {
    }

    public Director(String imgAddress, String directorName) {
        this.imgAddress = imgAddress;
        this.directorName = directorName;
    }
}
