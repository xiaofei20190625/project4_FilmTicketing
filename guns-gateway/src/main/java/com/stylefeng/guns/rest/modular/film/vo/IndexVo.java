package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/16 Time 17:09
 */
public class IndexVo implements Serializable {
    private int status;

    private String imgPre;

    private FilmIndexVo data;

    public IndexVo() {
    }

    public IndexVo(int status, String imgPre, FilmIndexVo data) {
        this.status = status;
        this.imgPre = imgPre;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImgPre() {
        return imgPre;
    }

    public void setImgPre(String imgPre) {
        this.imgPre = imgPre;
    }

    public FilmIndexVo getData() {
        return data;
    }

    public void setData(FilmIndexVo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IndexVo{" +
                "status=" + status +
                ", imgPre='" + imgPre + '\'' +
                ", data=" + data +
                '}';
    }
}
