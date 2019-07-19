package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.modular.film.dao.MtimeCatDictTMapper;
import com.stylefeng.guns.rest.modular.film.dao.MtimeFilmActorTMapper;
import com.stylefeng.guns.rest.modular.film.dao.MtimeFilmInfoTMapper;
import com.stylefeng.guns.rest.modular.film.dao.MtimeFilmTMapper;
import com.stylefeng.guns.rest.modular.film.model.*;
import com.stylefeng.guns.rest.modular.film.service.*;
import com.stylefeng.guns.rest.modular.film.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/16 Time 18:16
 */
@Component
@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    IMtimeFilmTService iMtimeFilmTService;
    @Autowired
    MtimeFilmTMapper filmTMapper;
    @Autowired
    MtimeFilmInfoTMapper filmInfoTMapper;
    @Autowired
    IMtimeSourceDictTService sourceDictTService;
    @Autowired
    IMtimeCatDictTService catDictTService;
    @Autowired
    IMtimeFilmInfoTService infoTService;
    @Autowired
    MtimeCatDictTMapper catDictTMapper;
    @Autowired
    MtimeFilmActorTMapper filmActorTMapper;
    //首页接口

    //影片条件列表查询接口

    //影片查询接口
    @Override
    public GetFilmVO getFilmVO(GetFilmCondition getFilmCondition) {
        Integer nowPage = getFilmCondition.getNowPage();
        Integer pageSize = getFilmCondition.getPageSize();
        Integer showType = getFilmCondition.getShowType();
        Integer catId = getFilmCondition.getCatId();
        Integer sourceId = getFilmCondition.getSourceId();
        Integer yearId = getFilmCondition.getYearId();
        EntityWrapper<MtimeFilmT> wrapper = new EntityWrapper<>();
        //影片状态
        wrapper.eq("film_status", showType);
        //排序方式
        switch (sourceId) {
            case 1:
                wrapper.orderBy("film_box_office");
                break;
            case 2:
                wrapper.orderBy("film_time");
                break;
            case 3:
                wrapper.orderBy("film_score");
        }
        //类型编号
        if (catId != 99) {
            wrapper.like("film_cats","#" + catId + "#");
        }
        //区域编号
        if (sourceId != 99) {
            wrapper.eq("film_source", sourceId);
        }
        //年代编号
        if (yearId != 99) {
            wrapper.eq("film_date", yearId);
        }

        //分页
        Page<MtimeFilmT> page = new Page<>(nowPage, pageSize);
        Page<MtimeFilmT> filmTPage = iMtimeFilmTService.selectPage(page, wrapper);
        List<MtimeFilmT> films = filmTPage.getRecords();
        List<FilmResponseVO> filmList = new LinkedList<>();
        for (MtimeFilmT film : films) {
            FilmResponseVO filmResponseVO = new FilmResponseVO();
            filmResponseVO.setFilmId(film.getUuid());
            filmResponseVO.setFilmType(film.getFilmType() + ", [0-2D,1-3D,2-3DIMAX,4-无]");
            filmResponseVO.setFilmName(film.getFilmName());
            filmResponseVO.setImgAddress(film.getImgAddress());
            filmResponseVO.setFilmScore(film.getFilmScore());
            filmList.add(filmResponseVO);
        }
        long totalPage = filmTPage.getTotal();
        GetFilmVO<List> getFilmVO = new GetFilmVO<>(0, "http://img.meetingshop.cn/", nowPage, totalPage, filmList);
        return getFilmVO;
    }


    //影片详情查询接口
    @Override
    public FilmDetailVO getFilmDetail(Integer searchType, String searchValue) {
        FilmDetailVO filmDetailVO = new FilmDetailVO();
        MtimeFilmT film;
        if (searchType == 0) {
            film = filmTMapper.selectById(searchValue);
        } else {
            film = filmTMapper.selectByName(searchValue);
        }
        MtimeFilmInfoT filmInfo = filmInfoTMapper.selectByFilmId(film.getUuid() + "");
        //票房
        Integer boxOffice = film.getFilmBoxOffice();
        String totalBox = getTotalBox(boxOffice);
        //info01影片类型
        String info01 = getFilmType(film);
        //info02产地/电影时间
        String info02 = getFilmOriginAndLength(film, filmInfo);
        //info03 上映时间和地点
        String info03 = getFilmDateAndPlace(film);
        //info04
        Info04 info04 = getInfo04(filmInfo);
        //imgVO
        ImgVO imgVO = getImgVO(filmInfo);


        filmDetailVO.setFilmId(film.getUuid());
        filmDetailVO.setFilmName(film.getFilmName());
        filmDetailVO.setImgAddress(film.getImgAddress());
        filmDetailVO.setScore(film.getFilmScore());
        filmDetailVO.setFilmEnName(filmInfo.getFilmEnName());
        filmDetailVO.setScoreNum(filmInfo.getFilmScoreNum() + "万人评分");
        filmDetailVO.setTotalBox(totalBox);
        filmDetailVO.setInfo01(info01);
        filmDetailVO.setInfo02(info02);
        filmDetailVO.setInfo03(info03);
        filmDetailVO.setInfo04(info04);
        filmDetailVO.setImgVO(imgVO);
        return filmDetailVO;
    }

    private ImgVO getImgVO(MtimeFilmInfoT filmInfo) {
        String filmImgs = filmInfo.getFilmImgs();
        String[] imgs = filmImgs.split(",");
        ImgVO imgVO = new ImgVO();
        imgVO.setMainImg(imgs[0]);
        imgVO.setImg01(imgs[1]);
        imgVO.setImg02(imgs[2]);
        imgVO.setImg03(imgs[3]);
        imgVO.setImg04(imgs[4]);
        return imgVO;
    }

    private Info04 getInfo04(MtimeFilmInfoT filmInfo) {
        String filmId = filmInfo.getFilmId();
        Integer directorId = filmInfo.getDirectorId();
        Info04 info04 = new Info04();
        FilmActors filmActors = new FilmActors();
        Actor actor = filmActorTMapper.selectByAId(directorId);
        Director director = new Director();
        director.setImgAddress(actor.getImgAddress());
        director.setDirectorName(actor.getDirectorName());
        filmActors.setDirector(director);
        List<MtimeFilmActorT> filmActorTList = filmActorTMapper.selectByFilmId(Integer.parseInt(filmId));
        List<Actor> actors1 = new LinkedList<>();
        for (MtimeFilmActorT filmActorT : filmActorTList) {
            Integer actorId = filmActorT.getActorId();
            Actor actor1 = filmActorTMapper.selectByAId(actorId);
            actors1.add(actor1);
        }
        filmActors.setActors(actors1);
        info04.setActors(filmActors);
        info04.setBiography(filmInfo.getBiography());
        return info04;
    }

    private String getFilmDateAndPlace(MtimeFilmT film) {
        StringBuffer info03 = new StringBuffer();
        Integer filmSource = film.getFilmSource();
        Date date = film.getFilmTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String filmTime = simpleDateFormat.format(date);
        info03.append(filmTime).append(" 大陆上映");
        return info03.toString();
    }

    private String getFilmOriginAndLength(MtimeFilmT film, MtimeFilmInfoT filmInfo) {
        StringBuffer info2 = new StringBuffer();
        Integer filmSource = film.getFilmSource();
        MtimeSourceDictT sourceDictT = sourceDictTService.selectById(filmSource);
        Integer filmLength = filmInfo.getFilmLength();
        info2.append(sourceDictT.getShowName()).append("/").append(filmLength).append("分钟");
        return info2.toString();
    }

    private String getFilmType(MtimeFilmT film) {
        String cats = film.getFilmCats();
        String[] filmCats = cats.split("#");
        List<MtimeCatDictT> catDictTS = catDictTMapper.selectByIds(filmCats);
        StringBuffer info01 = new StringBuffer();
        for (MtimeCatDictT catDictT : catDictTS) {
            info01.append(catDictT.getShowName() + " ,");
        }
        return info01.substring(0, info01.length() - 1);

    }

    private String getTotalBox(Integer boxOffice) {
        double filmBoxOffice;
        String totalBox;

        if (boxOffice < 100000000) {
            int i = boxOffice/10000;
            totalBox = i + "万";
            //filmBoxOffice = 1.0*filmBoxOffice/100000000;
            //totalBox = String.format("%.2f", boxOffice) + "亿";

        } else {
            filmBoxOffice = 1.0 * boxOffice/100000000;
            totalBox = String.format("%.2f", boxOffice) + "亿";
        }

        return totalBox;

    }


}
