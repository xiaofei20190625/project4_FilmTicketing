package com.stylefeng.guns.rest.modular.film.vo;


import lombok.Data;

import java.io.Serializable;
@Data
public class ResponseVO1<T> implements Serializable {
    Integer status;

    String imgPre;

    T data;

    public ResponseVO1() {
    }

    public ResponseVO1(Integer status, String imgPre, T data) {
        this.status = status;
        this.imgPre = imgPre;
        this.data = data;
    }
}
