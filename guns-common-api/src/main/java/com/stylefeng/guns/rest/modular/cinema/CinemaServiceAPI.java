package com.stylefeng.guns.rest.modular.cinema;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.modular.cinema.vo.*;


import java.util.List;

public interface CinemaServiceAPI {

    List<CinemaVo> getCinema(String brandId);

    //根据CinemaQueryVO查询影院列表
    Page<CinemaVo> getCinemas(CinemaQueryVo cinemaQueryVo);

    CinemaInfo getFields(int cinemaId);

    List<FilmList> getFilmList(int cinemaId);

    //获取所有电源信息和对应放映场次信息
    List<FilmList> getFilmInfoByCinemaId(int cinemaId);

    //根据条件获取品牌列表（除99以外其他数字都有效）
   List<BrandVo> getBrands(int brandId);

   //行政区域
   List<AreaVo> getAreas(int areaId);

   //影厅类型
   List<HallTypeVo> getHallTypes(int hallTypeId);

   //影院信息
   CinemaInfo getCinemaInfoById(int cinemaId);

   //放映信息
   HallInfoVo getFilmFieldInfo(int fieldId);

   //放映场次-->电源编号-->电影信息
   FilmInfo getFilmInfoByFieldId(int fieldId);

}
