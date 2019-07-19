package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/18 19:36
 * @Description TODO
 */
@Data
public class  CinemaQueryVo implements Serializable {
    private Integer  brandId  = 99;
    private Integer  districtId = 99;
    private Integer  hallType = 99;
    private Integer  pageSize = 12;
    private Integer  nowPage = 1;

    public CinemaQueryVo(Integer brandId, Integer districtId, Integer hallType, Integer pageSize, Integer nowPage) {
        this.brandId = brandId;
        this.districtId = districtId;
        this.hallType = hallType;
        this.pageSize = pageSize;
        this.nowPage = nowPage;
    }

    public CinemaQueryVo() {
    }
}
