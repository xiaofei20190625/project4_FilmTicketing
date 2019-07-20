package com.stylefeng.guns.rest.modular.cinema.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;


import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import com.stylefeng.guns.rest.modular.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.modular.cinema.vo.*;
import com.stylefeng.guns.rest.modular.order.OrderService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex_Cheng
 * @date 2019/7/17 15:57
 * @Description TODO
 */
@Component
@Service(interfaceClass = CinemaServiceAPI.class)
public class CinemaServiceImpl implements CinemaServiceAPI {

    @Autowired
    MtimeCinemaTMapper cinemaMapper;
    @Autowired
    MtimeHallFilmInfoTMapper hallFilmInfoMapper;
    @Autowired
    MtimeFieldTMapper fieldMapper;
    @Autowired
    MtimeBrandDictTMapper brandDictMapper;
    @Autowired
    MtimeAreaDictTMapper areaDictMapper;
    @Autowired
    MtimeHallDictTMapper hallDictMapper;
    @Reference(interfaceClass = OrderService.class)
    OrderService orderService;


   @Override
   public List<CinemaVo> getCinema(String brandId) {
       List<MtimeCinemaT> cinemaList;
       if (brandId == null){
           cinemaList = cinemaMapper.selectList(new EntityWrapper<>());
       }else
           cinemaList = cinemaMapper.queryByBrandId(Integer.parseInt(brandId));
       List<CinemaVo> data = new ArrayList<>();
       for (MtimeCinemaT cinema: cinemaList) {
           CinemaVo cinemaQueryVo = new CinemaVo();
           cinemaQueryVo.setUuid(cinema.getUuid() + "");
           cinemaQueryVo.setCinemaName(cinema.getCinemaName());
           cinemaQueryVo.setAddress(cinema.getCinemaAddress());
           cinemaQueryVo.setMinimumPrice(cinema.getMinimumPrice() + "");
           data.add(cinemaQueryVo);
       }
       return data;
   }

    @Override
    public CinemaInfo getFields(int cinemaId) {
        MtimeCinemaT cinema = cinemaMapper.selectByUUId(cinemaId);
        CinemaInfo cinemaInfo = new CinemaInfo();
        cinemaInfo.setCinemaId(cinema.getUuid());
        cinemaInfo.setCinemaName(cinema.getCinemaName());
        cinemaInfo.setCinemaAdress(cinema.getCinemaAddress());
        cinemaInfo.setCinemaPhone(cinema.getCinemaPhone());
        cinemaInfo.setImgUrl("cinemas/123.jpg");
        return  cinemaInfo;
    }

    @Override
    public List<FilmList> getFilmList(int cinemaId) {
        List<FilmList> filmInfoVoList = fieldMapper.getFilmList(cinemaId);
        return filmInfoVoList;
    }

    @Override
    public Page<CinemaVo> getCinemas(CinemaQueryVo cinemaQueryVo) {

        Page<MtimeCinemaT> page = new Page<>(cinemaQueryVo.getNowPage(),cinemaQueryVo.getPageSize());

        EntityWrapper<MtimeCinemaT> entityWrapper = new EntityWrapper<>();
        if(cinemaQueryVo.getBrandId()!=99){
            entityWrapper.eq("brand_id",cinemaQueryVo.getBrandId());
        }
        if(cinemaQueryVo.getAreaId()!=99){
            entityWrapper.eq("area_id",cinemaQueryVo.getAreaId());
        }
        if(cinemaQueryVo.getBrandId()!=99){
            entityWrapper.like("hall_ids","%#"+cinemaQueryVo.getHallType()+"#%");
        }


        List<MtimeCinemaT> cinemaList = cinemaMapper.selectPage(page,entityWrapper);

        List<CinemaVo> cinemaVoList = new ArrayList();

        for(MtimeCinemaT cinema:cinemaList){
            CinemaVo cinemaVo = new CinemaVo();
            cinemaVo.setUuid(cinema.getUuid() + "");
            cinemaVo.setAddress(cinema.getCinemaAddress());
            cinemaVo.setCinemaName(cinema.getCinemaName());
            cinemaVo.setMinimumPrice(cinema.getMinimumPrice() + "");
            cinemaVoList.add(cinemaVo);

        }

        //判断影院列表总数
        int totalCount = cinemaMapper.selectCount(entityWrapper);
        Page<CinemaVo> result = new Page<>();
        result.setRecords(cinemaVoList);
        result.setSize(cinemaQueryVo.getPageSize());
        result.setTotal(totalCount);

        return result;

    }

    @Override
    public List<BrandVo> getBrands(int brandId) {
       List<MtimeBrandDictT> brandDictList = brandDictMapper.selectList(null);
       List<BrandVo> brandVoList = new ArrayList<>();
        for (MtimeBrandDictT b: brandDictList) {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(b.getUuid() + "");
            brandVo.setBrandName(b.getShowName());
            if (brandId ==b.getUuid() ){
                brandVo.setActive(true);
            }else
                brandVo.setActive(false);
            brandVoList.add(brandVo);
        }
        return brandVoList;
    }

    @Override
    public List<AreaVo> getAreas(int areaId) {
        List<MtimeAreaDictT> areaDictList = areaDictMapper.selectList(null);
        List<AreaVo> areaVoList = new ArrayList<>();
        for (MtimeAreaDictT a: areaDictList) {
            AreaVo areaVo = new AreaVo();
            areaVo.setAreaId(a.getUuid() + "");
            areaVo.setAreaName(a.getShowName());
            if (areaId == a.getUuid()){
                areaVo.setActive(true);
            }else
                areaVo.setActive(false);
            areaVoList.add(areaVo);
        }
        return areaVoList;
    }

    @Override
    public List<HallTypeVo> getHallTypes(int cinemaId) {
        List<MtimeHallDictT> mtimeHallDictTS = hallDictMapper.selectList(null);
        List<HallTypeVo> hallTypeVoList = new ArrayList<>();
        for (MtimeHallDictT h:mtimeHallDictTS) {
            HallTypeVo hallTypeVo = new HallTypeVo();
            hallTypeVo.setHalltypeId(h.getUuid() + "");
            hallTypeVo.setHalltypeName(h.getShowName());
            if(cinemaId == h.getUuid()){
                hallTypeVo.setActive(true);
            }else
                hallTypeVo.setActive(false);
            hallTypeVoList.add(hallTypeVo);
        }
        return hallTypeVoList;
    }

    @Override
    public CinemaInfo getCinemaInfoById(int cinemaId) {
        MtimeCinemaT cinema = cinemaMapper.selectById(cinemaId);
        CinemaInfo cinemaInfo = new CinemaInfo();
        cinemaInfo.setCinemaId(cinema.getUuid());
        cinemaInfo.setCinemaPhone(cinema.getCinemaPhone());
        cinemaInfo.setCinemaAdress(cinema.getCinemaAddress());
        cinemaInfo.setCinemaName(cinema.getCinemaName());

        return cinemaInfo;
    }

    @Override
    public List<FilmList> getFilmInfoByCinemaId(int cinemaId) {
        return null;
    }

    @Override
    public HallInfoVo getFilmFieldInfo(int fieldId) {
        MtimeFieldT field = fieldMapper.selectById(fieldId);
        HallInfoVo hallInfoVo = new HallInfoVo();
        hallInfoVo.setHallFieldId(field.getUuid() + "");
        hallInfoVo.setHallName(field.getHallName());
        hallInfoVo.setPrice(field.getPrice() + "");
        String soldSeats = orderService.selectSoldSeats(fieldId);
        //Order提供已售座位
        hallInfoVo.setSoldSeats(soldSeats);
        return hallInfoVo;
    }

    @Override
    public FilmInfo getFilmInfoByFieldId(int fieldId) {
        MtimeHallFilmInfoT hallFilmInfoT = hallFilmInfoMapper.selectById(fieldId);
        FilmInfo filmInfo = new FilmInfo();
        filmInfo.setActors(hallFilmInfoT.getActors());
        filmInfo.setFilmCats(hallFilmInfoT.getFilmCats());
        filmInfo.setFilmFields(hallFilmInfoT.getUuid() + "");
        filmInfo.setFilmId(hallFilmInfoT.getFilmId() + "");
        filmInfo.setFilmLength(hallFilmInfoT.getFilmLength());
        filmInfo.setFilmName(hallFilmInfoT.getFilmName());
        filmInfo.setImgAddress(hallFilmInfoT.getImgAddress());
        filmInfo.setFilmType(hallFilmInfoT.getFilmLanguage());

        return filmInfo;
    }
}
