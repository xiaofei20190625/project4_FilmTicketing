package com.stylefeng.guns.rest.modular.order.service.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.modular.order.dao.MoocOrderTMapper;
import com.stylefeng.guns.rest.modular.order.model.MoocOrderT;
import com.stylefeng.guns.rest.modular.order.service.IMoocOrderTService;
import com.stylefeng.guns.rest.modular.order.service.IOrderTService;
import com.stylefeng.guns.rest.modular.order.persistence.model.OrderT;
import com.stylefeng.guns.rest.modular.order.persistence.dao.OrderTMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.modular.order.vo.NewOrderTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author xiaofei
 * @since 2019-07-19
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = IOrderTService.class)
public class OrderTServiceImpl  extends ServiceImpl<MoocOrderTMapper, MoocOrderT> implements IOrderTService{
    @Autowired
    MoocOrderTMapper orderTMapper;
    @Override
    public List<NewOrderTInfo> getOrderInfoPage(Integer uuid) {
        List<MoocOrderT> orderTList = orderTMapper.selectList(new EntityWrapper<MoocOrderT>().eq("order_user",uuid));
        ArrayList<NewOrderTInfo> newOrderTInfos = new ArrayList<>();
        for (MoocOrderT orderT : orderTList) {
            NewOrderTInfo newOrderTInfo = new NewOrderTInfo();
            //需要查对应的影院
            newOrderTInfo.setCinemaName(orderT.getCinemaId().toString());
            newOrderTInfo.setFieldTime(orderT.getOrderTime());
            newOrderTInfo.setOrderId(orderT.getUuid());
            newOrderTInfo.setOrderPrice(orderT.getOrderPrice());
            newOrderTInfo.setOrderStatus(orderT.getOrderStatus());
            newOrderTInfo.setSeatsName(orderT.getSeatsName());
            //需要查对应的名字
            newOrderTInfo.setFilmName(orderT.getFilmId().toString());
            newOrderTInfos.add(newOrderTInfo);
        }
        return newOrderTInfos;
    }
}
