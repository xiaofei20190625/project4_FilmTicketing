package com.stylefeng.guns.rest.modular.cinema.util;

import com.stylefeng.guns.rest.modular.cinema.vo.CinemaInfo;
import com.stylefeng.guns.rest.modular.cinema.vo.FilmList;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alex_Cheng
 * @date 2019/7/19 15:59
 * @Description TODO
 */

@Data
public class CinemaFiledVo implements Serializable {
    private CinemaInfo cinemaInfo;
    private List<FilmList> filmList;
}
