package com.stylefeng.guns.rest.modular.alipay.service;

public interface AliPayService {
    String getPayInfo(Integer orderId);

    Boolean getPayResult(Integer id, Integer orderId);
}
