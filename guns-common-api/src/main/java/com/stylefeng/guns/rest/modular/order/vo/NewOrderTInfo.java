package com.stylefeng.guns.rest.modular.order.vo;

import java.io.Serializable;
import java.util.Date;

public class NewOrderTInfo implements Serializable {

    /**
     * 主键订单编号
     */
    private String orderId;
    /**
     * 电影名字
     */
    private String filmName;

    /**
     * 下单时间
     */
    private Date fieldTime;

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
     * 0-待支付,1-已支付,2-已关闭
     */
    private String orderStatus;

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

    public Date getFieldTime() {
        return fieldTime;
    }

    public void setFieldTime(Date fieldTime) {
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
