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
    private static final long serialVersionUID = -8085164152707277266L;
    private Integer  brandId  = 99;
    private Integer  areaId = 99;
    private Integer  hallType = 99;
    private Integer  pageSize = 12;
    private Integer  nowPage = 1;

    public CinemaQueryVo(Integer brandId, Integer districtId, Integer hallType, Integer pageSize, Integer nowPage) {
        this.brandId = brandId;
        this.areaId = districtId;
        this.hallType = hallType;
        this.pageSize = pageSize;
        this.nowPage = nowPage;
    }

    public CinemaQueryVo() {
    }
}
