package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/19 11:11
 * @Description TODO
 */
@Data
public class BrandVo implements Serializable {
    private static final long serialVersionUID = 5598075764390694777L;
    private  String brandId;
    private  String brandName;
    private boolean isActive;
}
