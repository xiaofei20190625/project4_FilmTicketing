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
    private String uuid ;
    private String cinemaName ;
    private String address;
    private String minimumPrice;

    public CinemaVo(String uuid, String cinemaName, String address, String minimumPrice) {
        this.uuid = uuid;
        this.cinemaName = cinemaName;
        this.address = address;
        this.minimumPrice = minimumPrice;
    }

    public CinemaVo() {
    }
}
