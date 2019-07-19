package com.stylefeng.guns.rest.modular.cinema.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.modular.cinema.service.IMtimeCinemaTService;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaQueryVo;
import com.stylefeng.guns.rest.modular.cinema.util.CinemaResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 影院信息表 前端控制器
 * </p>
 *
 * @author alexcheng
 * @since 2019-07-16
 */
@RestController
@RequestMapping("/mtimeCinemaT")
public class MtimeCinemaTController {

    @Autowired
    IMtimeCinemaTService cinemaService;
        //1、根据CinemaQueryVO，查询影院列表
        //2、根据条件获取品牌列表
        //3、获取行政区域列表
        //4、获取影厅类型列表
        //5、根据影院编号，获取影院信息
        //6、获取所有电影的信息和对应的放映场次信息，根据影院编号
        //7、根据影院编号获取影院信息
        //8、根据放映场次ID获取放映信息
        //9、根据放映场次查询播放的电影编号，然后根据电影编号获取对应的电影信息

    ///cinema/getCinemas? brandId=30227&districtId=14&hallType=2&pageSize=12&nowPage=1
    @RequestMapping(value = "getCinemass" , method = RequestMethod.GET)
    public  Object getCinemas( String brandId ,  String districtId ,String hallType,  String  pageSize ,String nowPage){
        List<MtimeCinemaT> cinemaList;
        List<CinemaQueryVo> data = new ArrayList<>();
        if (brandId ==null){
            cinemaList = cinemaService.selectList(new EntityWrapper<>());
        }else
            cinemaList =  cinemaService.queryByBrandId(Integer.parseInt(brandId));
        for (MtimeCinemaT cinema: cinemaList) {
           //CinemaQueryVo cinemaQueryVo = new CinemaQueryVo();
           //cinemaQueryVo.setUuid(cinema.getUuid());
           //cinemaQueryVo.setCinemaName(cinema.getCinemaName());
           //cinemaQueryVo.setAddress(cinema.getCinemaAddress());
           //cinemaQueryVo.setMinimumPrice(cinema.getMinimumPrice());
           //data.add(cinemaQueryVo);
        }
        if (districtId ==null){
            districtId = "99";
        }
        if (hallType ==null){
            hallType = "99";
        }
        if (pageSize ==null)pageSize = "12";
        if (nowPage ==null)nowPage = "1";

        int totalPage = data.size();
        return  CinemaResponseVo.ok(data , Integer.parseInt(nowPage), totalPage);
    }


}

