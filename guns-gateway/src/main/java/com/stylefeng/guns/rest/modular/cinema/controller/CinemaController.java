package com.stylefeng.guns.rest.modular.cinema.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.stylefeng.guns.rest.modular.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.modular.cinema.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex_Cheng
 * @date 2019/7/17 17:11
 * @Description TODO
 */
@RestController
@RequestMapping("/cinema")
@Slf4j
public class CinemaController {

    @Reference(interfaceClass = CinemaServiceAPI.class, check = false)
    CinemaServiceAPI cinemaService;

    ///cinema/getCinemas? brandId=30227&districtId=14&hallType=2
    @RequestMapping(value = "/getCinemas", method = RequestMethod.GET)
    public Object getCinemas(CinemaQueryVo cinemaQueryVo ) {
        //String brandId ,  String districtId ,String hallType,  String  pageSize ,String nowPage
        try {
                    //CinemaQueryVo cinemaQueryVo = new CinemaQueryVo(Integer.parseInt(brandId) ,
                    //Integer.parseInt(districtId ),
                    //Integer.parseInt(hallType) ,
                    //Integer.parseInt(pageSize) ,
                    //Integer.parseInt(nowPage));
        CinemaQueryVo cinemaQueryVo1 = new CinemaQueryVo(cinemaQueryVo.getBrandId(),cinemaQueryVo.getDistrictId(),
                cinemaQueryVo.getHallType() , cinemaQueryVo.getPageSize() ,cinemaQueryVo.getNowPage());
        Page<CinemaVo> cinemas = cinemaService.getCinemas(cinemaQueryVo1);
        List<CinemaVo> result = cinemas.getRecords();
        if (result.size() == 0){
            return CinemaResponseVo.businessFault();
        }else
            return CinemaResponseVo.ok(result, cinemaQueryVo.getNowPage(), cinemaQueryVo.getPageSize());
    }catch (Exception e) {
            return CinemaResponseVo.systemException();

        }


    }

    ///cinema/getCondition


    ///cinema/getFields
    @RequestMapping(value = "getFields",method = {RequestMethod.GET , RequestMethod.POST})
    public Object getFields(int cinemaId){
        CinemaInfo cinemaInfo = cinemaService.getFields(cinemaId);
        List<FilmList> filmList = cinemaService.getFilmList(cinemaId);

        return  null;
    }


    ///cinema/getFieldInfo
}
