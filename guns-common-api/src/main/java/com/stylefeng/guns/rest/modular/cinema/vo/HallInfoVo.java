package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/19 11:20
 * @Description TODO
 */
@Data
public class HallInfoVo implements Serializable {
    private static final long serialVersionUID = -934512413677620116L;
    private String hallFieldId;
    private String hallName;
    private String price;
    private String seatFile = "seats/123214.json";
    private String soldSeats;  //-> 根据订单里面的seats_ids
}
