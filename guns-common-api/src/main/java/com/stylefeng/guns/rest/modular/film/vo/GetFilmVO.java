package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class GetFilmVO<T> implements Serializable {
    private int status;

    private String imgPre;

    private int nowPage;

    private long totalPage;

    T data;

    public GetFilmVO() {
    }

    public GetFilmVO(int status, String imgPre, int nowPage, long totalPage, T data) {
        this.status = status;
        this.imgPre = imgPre;
        this.nowPage = nowPage;
        this.totalPage = totalPage;
        this.data = data;
    }
}
