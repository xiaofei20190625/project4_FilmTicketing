package com.stylefeng.guns.rest.modular.order.service;

import com.stylefeng.guns.rest.modular.order.vo.NewOrderTInfo;
import java.util.List;

public interface IOrderTService {
    List<NewOrderTInfo> getOrderInfoPage(Integer usernameFromToken);
}
