package com.stylefeng.guns.rest.modular.order.service.serviceImpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.modular.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.modular.order.service.IOrderTService;
import com.stylefeng.guns.rest.modular.order.persistence.model.OrderT;
import com.stylefeng.guns.rest.modular.order.persistence.dao.OrderTMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.modular.order.vo.NewOrderTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class OrderTServiceImpl  implements IOrderTService {
    /*@Autowired
    OrderTMapper orderTMapper;*/
    @Override
    public List<NewOrderTInfo> getOrderInfoPage(Integer uuid) {
       // List<OrderT> orderTList = orderTMapper.selectList(new EntityWrapper<OrderT>().eq("order_user",uuid));

       /* for (OrderT orderT : orderTList) {
            NewOrderTInfo newOrderTInfo = new NewOrderTInfo();
            orderT.getCinemaId();

           *//* newOrderTInfo.setCinemaName();
            newOrderTInfo.setFieldTime();
            newOrderTInfo.setOrderId();
            newOrderTInfo.setOrderPrice();
            newOrderTInfo.getOrderStatus();
            newOrderTInfo.setSeatsName();
            newOrderTInfo.setFilmName();*//*

        }*/
        return null;
    }
}
