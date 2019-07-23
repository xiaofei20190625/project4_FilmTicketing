package com.stylefeng.guns.rest.modular.cinema.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/16 23:23
 * @Description TODO
 */
@Data
public class CinemaResponseVo implements Serializable {
    private static final long serialVersionUID = 719173633814238065L;
    private  Integer status = 0 ;
    private  Integer nowPage;
    private  Integer totalPage;
    private  Object data;
    private  String msg = "";
    private  String imgPre = "http://img.meetingshop.cn/";

    public static CinemaResponseVo ok(Object data , int nowPage , int totalPage){
        CinemaResponseVo cinemaVo = new CinemaResponseVo();
        cinemaVo.setStatus(0);
        cinemaVo.setNowPage(nowPage);
        cinemaVo.setTotalPage(totalPage);
        cinemaVo.setData(data);
        return cinemaVo;
    }

    public   static  CinemaResponseVo businessFault(){
        CinemaResponseVo cinemaVo = new CinemaResponseVo();
        cinemaVo.setStatus(1);
        cinemaVo.setMsg("影院信息查询失败" );
        return  cinemaVo;
    }

    public static CinemaResponseVo systemException(){
        CinemaResponseVo cinemaVo = new CinemaResponseVo();
        cinemaVo.setStatus(999);
        cinemaVo.setMsg("系统出现异常，请联系管理员");
        return  cinemaVo;
    }
}
