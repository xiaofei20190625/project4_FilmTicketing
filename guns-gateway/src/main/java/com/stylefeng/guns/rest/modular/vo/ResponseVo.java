package com.stylefeng.guns.rest.modular.vo;

import java.io.Serializable;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/15 Time 21:59
 */
public class ResponseVo implements Serializable {
    private static final long serialVersionUID = -1594901999968321760L;

    private Object data;

    private Integer status;

    private String msg;

    public ResponseVo() {
    }

    public ResponseVo(Object data, Integer status, String msg) {
        this.data = data;
        this.status = status;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    //成功
    public static ResponseVo ok(Object data, String msg){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(data);
        responseVo.setMsg(msg);
        responseVo.setStatus(0);
        return responseVo;
    }

    //业务异常1：未获取到数据
    public static ResponseVo fail(String msg){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMsg(msg);
        responseVo.setStatus(1);
        return responseVo;
    }

    //业务异常2：只获取到了部分数据
    public static ResponseVo fail(Object data, String msg){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(data);
        responseVo.setMsg(msg);
        responseVo.setStatus(1);
        return responseVo;
    }

    public static ResponseVo serverError(){
        return new ResponseVo(null, 999, "系统异常，请联系管理员");
    }

    @Override
    public String toString() {
        return "ResponseVo{" +
                "data=" + data +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
