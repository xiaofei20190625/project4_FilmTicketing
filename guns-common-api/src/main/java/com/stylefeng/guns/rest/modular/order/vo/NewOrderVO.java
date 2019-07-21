package com.stylefeng.guns.rest.modular.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/18 Time 20:52
 */
public class NewOrderVO implements Serializable {

    private static final long serialVersionUID = -419045177273841190L;

    /**
     * 主键订单编号
     */
    private String orderId;
    /**
     * 电影名字
     */
    private String filmName;

    /**
     * 场次开演时间
     */
    private String fieldTime;

    /**
     * 电影院名字
     */
    private String cinemaName;
    /**
     * 座位名称
     */
    private String seatsName;
    /**
     * 订单总金额
     */
    private Double orderPrice;
    /**
     * 订单时间戳
     */
    private String orderTimestamp;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFieldTime() {
        return fieldTime;
    }

    public void setFieldTime(String fieldTime) {
        this.fieldTime = fieldTime;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getSeatsName() {
        return seatsName;
    }

    public void setSeatsName(String seatsName) {
        this.seatsName = seatsName;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(String orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", filmName='" + filmName + '\'' +
                ", fieldTime=" + fieldTime +
                ", cinemaName='" + cinemaName + '\'' +
                ", seatsName='" + seatsName + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderTimestamp='" + orderTimestamp + '\'' +
                '}';
    }
}
