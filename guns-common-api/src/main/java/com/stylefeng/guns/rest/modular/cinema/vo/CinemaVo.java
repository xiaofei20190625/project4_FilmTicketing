package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/16 23:11
 * @Description TODO
 */
@Data
public class CinemaVo implements Serializable {
    private static final long serialVersionUID = -1812001007592031814L;
    private String uuid ;
    private String cinemaName ;
    private String cinemaAddress;
    private String minimumPrice;

    public CinemaVo(String uuid, String cinemaName, String address, String minimumPrice) {
        this.uuid = uuid;
        this.cinemaName = cinemaName;
        this.cinemaAddress = address;
        this.minimumPrice = minimumPrice;
    }

    public CinemaVo() {
    }
}
