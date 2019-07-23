package com.stylefeng.guns.rest.modular.order.vo;


public class OrderResponseVO {
    Integer status;
    String msg;
    Object data;
    String imgPre;
    Integer nowPage;
    Integer totalPage;

    public String getImgPre() {
        return imgPre;
    }

    public void setImgPre(String imgPre) {
        this.imgPre = imgPre;
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

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

    public static OrderResponseVO ok(Object data,Integer nowPage,Integer totalPage) {
        OrderResponseVO orderResponseVO = new OrderResponseVO();
        orderResponseVO.setData(data);
        orderResponseVO.setStatus(0);
        orderResponseVO.setMsg("");
        orderResponseVO.setImgPre("");
        orderResponseVO.setNowPage(nowPage);
        orderResponseVO.setTotalPage(totalPage);
        return orderResponseVO;
    }
}
