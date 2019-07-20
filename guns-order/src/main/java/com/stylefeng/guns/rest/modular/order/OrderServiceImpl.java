package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.rest.modular.order.dao.MoocOrderTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/18 Time 20:46
 */
@Component
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    MoocOrderTMapper orderMapper;
    /**
     * 判断座位信息是否有效
     * @param filedId
     * @param seatId
     * @return
     */
    @Override
    public Boolean isTrueSeats(String filedId, String seatId) {
        //获取到了fieldId，从mtime_field_t表里面拿到该场次在哪个厅放映

        return null;
    }

    @Override
    public String selectSoldSeats(int fieldId) {
        return orderMapper.selectSoldSeatsByFieldId(fieldId);

    }
}
