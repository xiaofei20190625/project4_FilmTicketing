package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/15 Time 22:22
 */
public class FilmVo<T> implements Serializable {
    private static final long serialVersionUID = 7660600729298688090L;

    private int filmNum;

    private List<T> filmInfo;

    public int getFilmNum() {
        return filmNum;
    }

    public void setFilmNum(int filmNum) {
        this.filmNum = filmNum;
    }

    public List<T> getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(List<T> filmInfo) {
        this.filmInfo = filmInfo;
    }

    @Override
    public String toString() {
        return "FilmVo{" +
                "filmNum=" + filmNum +
                ", filmInfo=" + filmInfo +
                '}';
    }
}
