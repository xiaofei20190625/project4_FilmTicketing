package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/18 Time 20:43
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Reference(interfaceClass = OrderService.class, check = false)
    OrderService orderService;

    @RequestMapping(value = "buyTickets", params = {"fieldId", "soldSeats", "seatsName"}, method = RequestMethod.POST)
    public ResponseVo buyTickets(String fieldId, String soldSeats, String seatsName){
        ResponseVo responseVo = null;
        //先判断传来的座位号是否是真实有效的
        Boolean isTrueSeats = orderService.isTrueSeats(fieldId, soldSeats);
        if (!isTrueSeats){
            responseVo = ResponseVo.fail("不存在此座位！");
            return responseVo;
        }
        //然后判断该座位是否是已经售出，防止重复下单

        return responseVo;
    }

}
