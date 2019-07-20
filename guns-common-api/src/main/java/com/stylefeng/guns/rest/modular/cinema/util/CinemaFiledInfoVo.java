package com.stylefeng.guns.rest.modular.cinema.util;

import com.stylefeng.guns.rest.modular.cinema.vo.CinemaInfo;
import com.stylefeng.guns.rest.modular.cinema.vo.FilmInfo;
import com.stylefeng.guns.rest.modular.cinema.vo.HallInfoVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/19 18:04
 * @Description TODO
 */
@Data
public class CinemaFiledInfoVo implements Serializable {
    private CinemaInfo cinemaInfo;
    private HallInfoVo hallInfo;
    private FilmInfo filmInfo ;
}
