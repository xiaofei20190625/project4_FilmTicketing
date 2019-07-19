package com.stylefeng.guns.rest.modular.cinema.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;

import com.stylefeng.guns.rest.modular.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.modular.cinema.util.CinemaConditionResponseVo;
import com.stylefeng.guns.rest.modular.cinema.util.CinemaFiledInfoVo;
import com.stylefeng.guns.rest.modular.cinema.util.CinemaFiledVo;
import com.stylefeng.guns.rest.modular.cinema.util.CinemaResponseVo;
import com.stylefeng.guns.rest.modular.cinema.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //String brandId ,  String districtId ,String hallType,  String  pageSize ,String nowPage
    @RequestMapping(value = "/getCinemas", method = RequestMethod.GET)
    public Object getCinemas(CinemaQueryVo cinemaQueryVo ) {
        try {
            Page<CinemaVo> cinemas = cinemaService.getCinemas(cinemaQueryVo);
        List<CinemaVo> result = cinemas.getRecords();
        if (result.size() == 0){
            return CinemaResponseVo.businessFault();
        }else{
            Integer nowPage = cinemaQueryVo.getNowPage();
            Integer pageSize = cinemaQueryVo.getPageSize();
            int totalPage = result.size() / pageSize  + 1 ;
            return CinemaResponseVo.ok(result , nowPage ,totalPage);
        }
    }catch (Exception e) {
            return CinemaResponseVo.systemException();
        }
    }

    ///cinema/getCondition
    @RequestMapping(value = "getCondition" ,method = RequestMethod.GET)
    public Object getCondition(CinemaQueryVo cinemaQueryVo){
        List<BrandVo> brandList = cinemaService.getBrands(cinemaQueryVo.getBrandId());
        List<AreaVo> areaList = cinemaService.getAreas(cinemaQueryVo.getDistrictId());
        List<HallTypeVo> halltypeList = cinemaService.getHallTypes(cinemaQueryVo.getBrandId());
        CinemaConditionResponseVo responseVo = new CinemaConditionResponseVo();
        responseVo.setBrandList(brandList);
        responseVo.setAreaList(areaList);
        responseVo.setHalltypeList(halltypeList);
        CinemaResponseVo cinemaResponseVo = new CinemaResponseVo();
        cinemaResponseVo.setData(responseVo);
        cinemaResponseVo.setImgPre("");//imgPre: ""

        cinemaResponseVo.setNowPage(null);//nowPage: 0

        cinemaResponseVo.setTotalPage(null);//totalPage: 0
        return  cinemaResponseVo;
    }


    ///cinema/getFields
    @RequestMapping(value = "getFields",method = {RequestMethod.GET , RequestMethod.POST})
    public CinemaResponseVo getFields(int cinemaId){
        CinemaInfo cinemaInfo = cinemaService.getFields(cinemaId);
        List<FilmList> filmList = cinemaService.getFilmList(cinemaId);

        CinemaFiledVo cinemaFiledVo = new CinemaFiledVo();
        cinemaFiledVo.setCinemaInfo(cinemaInfo);
        cinemaFiledVo.setFilmList(filmList);
        CinemaResponseVo cinemaResponseVo = new CinemaResponseVo();
        cinemaResponseVo.setData(cinemaFiledVo);
        cinemaResponseVo.setNowPage(null);

        cinemaResponseVo.setTotalPage(null);
        return  cinemaResponseVo;

    }


    ///cinema/getFieldInfo
    //http://192.168.2.100/cinema/getFieldInfo?cinemaId=1&fieldId=1
    @RequestMapping(value = "getFieldInfo" ,method = RequestMethod.POST)
    public CinemaResponseVo getFieldInfo(int cinemaId , int fieldId){
        CinemaInfo cinemaInfo = cinemaService.getCinemaInfoById(cinemaId);
        FilmInfo filmInfo = cinemaService.getFilmInfoByFieldId(fieldId);
        HallInfoVo hallInfo = cinemaService.getFilmFieldInfo(fieldId);
        CinemaFiledInfoVo infoVo = new CinemaFiledInfoVo();
        infoVo.setCinemaInfo(cinemaInfo);
        infoVo.setFilmInfo(filmInfo);
        infoVo.setHallInfo(hallInfo);
        CinemaResponseVo responseVo = new CinemaResponseVo();
        responseVo.setData(infoVo);
        responseVo.setNowPage(null);

        responseVo.setTotalPage(null);
        return responseVo;
    }
}
