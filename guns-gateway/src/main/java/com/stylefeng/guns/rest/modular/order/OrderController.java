package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "buyTickets", params = {"fieldId", "soldSeats", "seatsName"})
    public ResponseVo buyTickets(String fieldId, String soldSeats, String seatsName){
        Boolean isTrueSeats = orderService.isTrueSeats(fieldId, soldSeats);
        System.out.println(isTrueSeats);
        ResponseVo responseVo = new ResponseVo();
        return responseVo;
    }

}
