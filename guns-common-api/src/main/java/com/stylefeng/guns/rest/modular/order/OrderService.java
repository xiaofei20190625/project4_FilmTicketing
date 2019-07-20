package com.stylefeng.guns.rest.modular.order;

import com.stylefeng.guns.rest.modular.order.vo.NewOrderVO;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/18 Time 20:45
 */
public interface OrderService {
    /**
     * 判断座位信息是否有效
     * @param fieldId
     * @param seatId
     * @return
     */
    Boolean isTrueSeats(String fieldId, String seatId);

    /**
     * 判断座位是否已经卖出
     * @param field
     * @param seatId
     * @return
     */
    Boolean isSoldSeats(String field, String seatId);

    /**
     * 新建一个订单
     * @param fieldId
     * @param soldSeats
     * @param seatsName
     * @param userId
     * @return
     */
    NewOrderVO createNewOrder(String fieldId, String soldSeats, String seatsName, Integer userId);
}
