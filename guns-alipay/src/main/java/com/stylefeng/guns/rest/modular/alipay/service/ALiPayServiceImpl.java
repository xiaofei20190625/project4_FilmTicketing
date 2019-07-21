package com.stylefeng.guns.rest.modular.alipay.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.modular.alipay.ALiPay;
import com.stylefeng.guns.rest.modular.order.model.MoocOrderT;
import com.stylefeng.guns.rest.modular.order.service.IMoocOrderTService;
import org.springframework.beans.factory.annotation.Autowired;


@com.alibaba.dubbo.config.annotation.Service(interfaceClass = AliPayService.class)
public class ALiPayServiceImpl implements AliPayService {
    @Autowired
    IMoocOrderTService iMoocOrderTService;
    @Override
    public String getPayInfo(Integer orderId) {

        MoocOrderT orderT = iMoocOrderTService.selectOne(new EntityWrapper<MoocOrderT>().eq("UUID", orderId));
        return new ALiPay().test_trade_precreate(orderT);
    }

    @Override
    public Boolean getPayResult(Integer orderId,Integer tryNum) {

        Boolean orderStatus = new ALiPay().test_trade_query(orderId);
        if (orderStatus){
            MoocOrderT orderT = iMoocOrderTService.selectOne(new EntityWrapper<MoocOrderT>().eq("UUID", orderId));
            return iMoocOrderTService.update(orderT,new EntityWrapper<MoocOrderT>().eq("order_status", 1));
        }
        if (tryNum+1>4){
            MoocOrderT orderT = iMoocOrderTService.selectOne(new EntityWrapper<MoocOrderT>().eq("UUID", orderId));
            return iMoocOrderTService.update(orderT,new EntityWrapper<MoocOrderT>().eq("order_status", 3));
        }
        return false;
    }
}
