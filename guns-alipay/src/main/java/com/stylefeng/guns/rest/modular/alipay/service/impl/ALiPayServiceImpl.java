package com.stylefeng.guns.rest.modular.alipay.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.stylefeng.guns.rest.modular.alipay.ALiPay;
import com.stylefeng.guns.rest.modular.alipay.service.AliPayService;

import com.stylefeng.guns.rest.modular.alipay.service.NewMoocOrderTService;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
@Service(interfaceClass = AliPayService.class)
public class ALiPayServiceImpl implements AliPayService {
    @Autowired
    NewMoocOrderTService iMoocOrderTService;
    @Autowired
    ALiPay aLiPay;
    @Override
    public String getPayInfo(String orderId) {

        MoocOrderT orderT = iMoocOrderTService.selectOneOrder(orderId);
        String s = aLiPay.test_trade_precreate(orderT);
        return s;
    }

    @Override
    public Boolean getPayResult(String orderId,Integer tryNum) {

        Boolean orderStatus = new ALiPay().test_trade_query(orderId);
        if (orderStatus){
            MoocOrderT orderT = iMoocOrderTService.selectOneOrder(orderId);
            orderT.setOrderStatus(1);
            return iMoocOrderTService.updateNew(orderT);
        }
        if (tryNum+1>4){
            MoocOrderT orderT = iMoocOrderTService.selectOneOrder(orderId);
            orderT.setOrderStatus(3);
            return iMoocOrderTService.updateNew(orderT);
        }
        return false;
    }
}
