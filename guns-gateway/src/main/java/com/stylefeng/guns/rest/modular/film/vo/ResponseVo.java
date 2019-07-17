package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/15 Time 21:59
 */
public class ResponseVo implements Serializable {
    private Integer status;

    private String msg;

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

    @Override
    public String toString() {
        return "ResponseVo{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
