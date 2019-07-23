package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/19 11:13
 * @Description TODO
 */
@Data
public class HallTypeVo implements Serializable {
    private static final long serialVersionUID = 4473029813829059018L;
    private  String halltypeId;
    private  String halltypeName;
    private boolean isActive;
}
