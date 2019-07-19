package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Actor implements Serializable {

    private String imgAddress;

    private String directorName;

    private String roleName;

    public Actor() {
    }

    public Actor(String imgAddress, String directorName, String roleName) {
        this.imgAddress = imgAddress;
        this.directorName = directorName;
        this.roleName = roleName;
    }
}
