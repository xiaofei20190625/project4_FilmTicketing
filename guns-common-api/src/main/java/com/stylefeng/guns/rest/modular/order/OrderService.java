package com.stylefeng.guns.rest.modular.order;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/18 Time 20:45
 */
public interface OrderService {
    /**
     * 判断座位信息是否有效
     * @param filedId
     * @param seatId
     * @return
     */
    Boolean isTrueSeats(String filedId, String seatId);

    String selectSoldSeats(int fieldId);
}
