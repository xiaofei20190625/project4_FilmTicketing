package com.stylefeng.guns.rest.modular.alipay.vo;

public class PayStatusVO {
    Integer status;
    Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static PayStatusVO ok(Object data){
        PayStatusVO payVO = new PayStatusVO();
        payVO.setData(data);
        payVO.setStatus(0);
        return payVO;
    }
}

