package com.stylefeng.guns.rest.modular.film.vo;


import lombok.Data;

import java.io.Serializable;
@Data
public class ResponseVO1<T> implements Serializable {
    Integer status;

    String imgPre;

    String msg;

    String nowPage;

    String totalPage;

    T data;

    public ResponseVO1() {
    }

    public ResponseVO1(Integer status, String imgPre, String msg, String nowPage, String totalPage, T data) {
        this.status = status;
        this.imgPre = imgPre;
        this.msg = msg;
        this.nowPage = nowPage;
        this.totalPage = totalPage;
        this.data = data;
    }
}
