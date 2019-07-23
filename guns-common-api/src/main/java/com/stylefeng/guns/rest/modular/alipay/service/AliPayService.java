package com.stylefeng.guns.rest.modular.alipay.service;

public interface AliPayService {
    String getPayInfo(String orderId);

    Boolean getPayResult(String orderId,Integer id);
}
