package com.stylefeng.guns.rest.modular.alipay.vo;


public class QRcode {
    String orderId;
    String  QRCodeAddress;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getQRCodeAddress() {
        return QRCodeAddress;
    }

    public void setQRCodeAddress(String QRCodeAddress) {
        this.QRCodeAddress = QRCodeAddress;
    }
}
