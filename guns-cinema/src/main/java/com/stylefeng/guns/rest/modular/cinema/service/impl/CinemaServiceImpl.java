package com.stylefeng.guns.rest.modular.cinema.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;


import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeFieldTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeHallDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeHallFilmInfoTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallDictT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallFilmInfoT;
import com.stylefeng.guns.rest.modular.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.modular.cinema.vo.*;
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

   @Override
   public List<CinemaQueryVo> getCinema(String brandId) {
       List<MtimeCinemaT> cinemaList;
       if (brandId == null){
           cinemaList = cinemaMapper.selectList(new EntityWrapper<>());
       }else
           cinemaList = cinemaMapper.queryByBrandId(Integer.parseInt(brandId));
       List<CinemaQueryVo> data = new ArrayList<>();
       for (MtimeCinemaT cinema: cinemaList) {
           //CinemaQueryVo cinemaQueryVo = new CinemaQueryVo();
           //cinemaQueryVo.setUuid(cinema.getUuid());
           //cinemaQueryVo.setCinemaName(cinema.getCinemaName());
           //cinemaQueryVo.setAddress(cinema.getCinemaAddress());
           //cinemaQueryVo.setMinimumPrice(cinema.getMinimumPrice());
           //data.add(cinemaQueryVo);
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
    public List<FilmFields> getFilmFields(int cinemaId) {
        return null;
    }

    @Override
    public List<FilmList> getFilmList(int cinemaId) {
        List<FilmList> filmLists  = new ArrayList<>();
        List<MtimeFieldT> fieldList = fieldMapper.selectByCinemaId(cinemaId);
        for (MtimeFieldT field:fieldList) {
            Integer filmId = field.getFilmId();
            MtimeHallFilmInfoT hallFilmInfo = hallFilmInfoMapper.selectByFilmId(filmId);
            FilmList filmList = new FilmList();
            filmList.setFilmId(field.getFilmId());
            filmList.setFilmName(hallFilmInfo.getFilmName());
            filmList.setFilmLength(hallFilmInfo.getFilmLength());
            filmList.setFilmType(hallFilmInfo.getFilmLanguage());
            filmList.setFilmCats(hallFilmInfo.getFilmCats());
            filmList.setActors(hallFilmInfo.getActors());

        }

        return null;
    }

    @Override
    public Page<CinemaVo> getCinemas(CinemaQueryVo cinemaQueryVo) {
        Integer pageSize = cinemaQueryVo.getPageSize();
        Integer nowPage = cinemaQueryVo.getNowPage();
        Page<CinemaVo> page = new Page<>(nowPage , pageSize);
        Integer brandId = cinemaQueryVo.getBrandId();
        Integer districtId = cinemaQueryVo.getDistrictId();
        Integer hallType = cinemaQueryVo.getHallType();


        EntityWrapper<MtimeCinemaT> entityWrapper = new EntityWrapper<>();
        if(cinemaQueryVo.getBrandId()!=99){
            entityWrapper.eq("brand_id",brandId);
        }
        if(cinemaQueryVo.getDistrictId()!=99){
            entityWrapper.eq("area_id",districtId);
        }
        if(cinemaQueryVo.getBrandId()!=99){
            entityWrapper.like("hall_ids","%#"+hallType+"#%");
        }
        List<MtimeCinemaT> cinemaList = cinemaMapper.selectPage(page,entityWrapper);
        List<CinemaVo> cinemaVoList = new ArrayList();
        for (MtimeCinemaT c:cinemaList) {
            CinemaVo cinemaVo = new CinemaVo();
            cinemaVo.setUuid(c.getUuid() + "");
            cinemaVo.setAddress(c.getCinemaAddress());
            cinemaVo.setCinemaName(c.getCinemaName());
            cinemaVo.setMinimumPrice(c.getMinimumPrice() + "");
            cinemaVoList.add(cinemaVo);
        }
        //判断影院列表总数
        int totalCount = cinemaMapper.selectCount(entityWrapper);
        Page<CinemaVo> result = new Page<>();
        result.setRecords(cinemaVoList);
        result.setSize(pageSize);
        result.setTotal(totalCount);

        return result;
    }

//   @Override
//   public List<BrandVo> getBrands(int brandId) {
//       return null;
//   }

//   @Override
//   public List<AreaVo> getAreas(int areaId) {
//       return null;
//   }

//   @Override
//   public List<HallTypeVo> getHallTypes(int cinemaId) {
//       return null;
//   }

//   @Override
//   public CinemaInfoVo getCinemaInfoById(int cinemaId) {
//       return null;
//   }

//   @Override
//   public List<FilmInfoVo> getFilmInfoByCinemaId(int cinemaId) {
//       return null;
//   }

//   @Override
//   public HallInfoVo getFilmFieldInfo(int fieldId) {
//       return null;
//   }

//   @Override
//   public FilmInfoVo getFilmInfoByFieldId(int fieldId) {
//       return null;
//   }
}
