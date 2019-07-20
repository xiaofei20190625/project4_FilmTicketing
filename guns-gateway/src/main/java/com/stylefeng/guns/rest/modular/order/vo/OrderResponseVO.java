package com.stylefeng.guns.rest.modular.order.vo;


public class OrderResponseVO {
    Integer status;
    String msg;
    Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static OrderResponseVO ok(Object data) {
        OrderResponseVO orderResponseVO = new OrderResponseVO();
        orderResponseVO.setData(data);
        orderResponseVO.setStatus(0);
        orderResponseVO.setMsg("");
        return orderResponseVO;
    }
}
