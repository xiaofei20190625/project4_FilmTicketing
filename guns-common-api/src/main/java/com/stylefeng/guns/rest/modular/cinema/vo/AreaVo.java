package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/19 11:12
 * @Description TODO
 */
@Data
public class AreaVo implements Serializable {
    private static final long serialVersionUID = 537640613400040389L;
    private  String areaId;
    private  String areaName;
    private boolean isActive;
}
