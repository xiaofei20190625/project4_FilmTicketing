package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ResponseVO2<T> implements Serializable {
    private int status;

    private String imgPre;

    private int nowPage;

    private long totalPage;

    T data;
}
