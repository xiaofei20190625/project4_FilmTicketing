package com.stylefeng.guns.rest.modular.film;

import com.alibaba.druid.support.monitor.annotation.MField;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.modular.film.FilmService;
import com.stylefeng.guns.rest.modular.film.model.*;
import com.stylefeng.guns.rest.modular.film.service.*;
import com.stylefeng.guns.rest.modular.film.util.DataForgeryUtil;
import com.stylefeng.guns.rest.modular.film.vo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/16 Time 18:16
 */
@Service
@org.springframework.stereotype.Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    IMtimeBannerTService bannerTService;
    @Autowired
    IMtimeFilmTService filmTService;
    @Autowired
    IMtimeFilmInfoTService filmInfoTService;
    @Autowired
    IMtimeCatDictTService catDictTService;
    @Autowired
    IMtimeSourceDictTService sourceDictTService;
    @Autowired
    IMtimeYearDictTService yearDictTService;
    //首页接口
    //--获取首页banner信息
    @Override
    public List<Banner> getIndexBanners() {
        List<MtimeBannerT> mtimeBannerTS = bannerTService.selectList(null);
        //新建一个首页用的bannerList
        List<Banner> banners = new ArrayList<>();
        //遍历dao层查到的list，封装到bannerList中去
        for (MtimeBannerT b : mtimeBannerTS){
            Banner banner = new Banner();
            banner.setBannerId(b.getUuid());
            banner.setBannerAddress(b.getBannerAddress());
            banner.setBannerUrl(b.getBannerUrl());
            banners.add(banner);
        }
        return banners;
    }

    @Override
    public FilmVo<HotFilm> getIndexHotFilms() {
        List<MtimeFilmT> mtimeFilmInfoTList = filmTService.selectList(null);
        int count = filmTService.selectCount(null);
        List<HotFilm> hotFilms = new ArrayList<>();
        for (MtimeFilmT filmT : mtimeFilmInfoTList){
            HotFilm hotFilm = new HotFilm();
            hotFilm.setFilmId(filmT.getUuid());
            hotFilm.setFilmName(filmT.getFilmName());
            hotFilm.setFilmType(filmT.getFilmType());
            hotFilm.setFilmScore(filmT.getFilmScore());
            hotFilm.setImgAddress(filmT.getImgAddress());
            hotFilms.add(hotFilm);
        }

        FilmVo<HotFilm> hotFilmVo = new FilmVo<>();
        hotFilmVo.setFilmNum(count);
        hotFilmVo.setFilmInfo(hotFilms);
        return hotFilmVo;
    }

    @Override
    public FilmVo<SoonFilm> getIndexSoonFilms() {
        List<MtimeFilmT> mtimeFilmInfoTList = filmTService.selectList(null);
        int count = filmTService.selectCount(null);
        List<SoonFilm> soonFilms = new ArrayList<>();
        for (MtimeFilmT filmT : mtimeFilmInfoTList){
            SoonFilm soonFilm = new SoonFilm();
            soonFilm.setFilmId(filmT.getUuid());
            soonFilm.setFilmType(filmT.getFilmType());
            soonFilm.setImgAddress(filmT.getImgAddress());
            soonFilm.setFilmName(filmT.getFilmName());
            soonFilm.setExpectNum(DataForgeryUtil.getRandomExpectNum());
            soonFilm.setShowTime(DataForgeryUtil.getRandomShowTime());
            soonFilms.add(soonFilm);
        }
        FilmVo<SoonFilm> soonFilmVo = new FilmVo<>();
        soonFilmVo.setFilmNum(count);
        soonFilmVo.setFilmInfo(soonFilms);
        return soonFilmVo;
    }

    @Override
    public List<BoxRanking> getIndexBoxRanking() {
        List<MtimeFilmT> mtimeFilmInfoTList = filmTService.selectList(null);
        List<BoxRanking> boxRankings = new ArrayList<>();
        for (MtimeFilmT filmT : mtimeFilmInfoTList){
            BoxRanking boxRanking = new BoxRanking();
            boxRanking.setFilmId(filmT.getUuid());
            boxRanking.setImgAddress(filmT.getImgAddress());
            boxRanking.setFilmName(filmT.getFilmName());
            boxRanking.setBoxNum(filmT.getFilmBoxOffice());
            boxRankings.add(boxRanking);
        }
        return boxRankings;
    }

    @Override
    public List<ExpectRanking> getIndexExpectRanking() {
        List<MtimeFilmT> mtimeFilmInfoTList = filmTService.selectList(null);
        List<ExpectRanking> expectRankings = new ArrayList<>();
        for (MtimeFilmT filmT : mtimeFilmInfoTList){
            ExpectRanking expectRanking = new ExpectRanking();
            expectRanking.setFilmId(filmT.getUuid());
            expectRanking.setImgAddress(filmT.getImgAddress());
            expectRanking.setFilmName(filmT.getFilmName());
            expectRanking.setExpectNum(DataForgeryUtil.getRandomExpectNum());
            expectRankings.add(expectRanking);
        }
        return expectRankings;
    }

    @Override
    public List<Top100> getIndexTop100s() {
        List<MtimeFilmT> mtimeFilmInfoTList = filmTService.selectList(new EntityWrapper<MtimeFilmT>()
                .orderBy("film_score").last("desc"));
        List<Top100> top100s = new ArrayList<>();
        for (MtimeFilmT filmT : mtimeFilmInfoTList){
            Top100 top100 = new Top100();
            top100.setFilmId(filmT.getUuid());
            top100.setImgAddress(filmT.getImgAddress());
            top100.setFilmName(filmT.getFilmName());
            top100.setScore(filmT.getFilmScore());
            top100s.add(top100);
        }
        return top100s;
    }

    //--获取电影的分类信息
    @Override
    public List<CatInfo> getCatInfo(int catId) {
        List<MtimeCatDictT> mtimeCatDictTS = catDictTService.selectList(null);

        List<CatInfo> catInfos = new ArrayList<>();
        for (MtimeCatDictT c : mtimeCatDictTS){
            CatInfo catInfo = new CatInfo();
            if (catId == c.getUuid()){
                catInfo.setActive(true);
            }else {
                catInfo.setActive(false);
            }
            catInfo.setCatId(c.getUuid());
            catInfo.setCatName(c.getShowName());
            catInfos.add(catInfo);
        }
        return catInfos;
    }

    @Override
    public List<SourceInfo> getSourceInfo(int sourceId) {
        List<MtimeSourceDictT> mtimeSourceDictTS = sourceDictTService.selectList(null);

        List<SourceInfo> sourceInfos = new ArrayList<>();
        for (MtimeSourceDictT s : mtimeSourceDictTS){
            SourceInfo sourceInfo = new SourceInfo();
            if (sourceId == s.getUuid()){
                sourceInfo.setActive(true);
            }else {
                sourceInfo.setActive(false);
            }
            sourceInfo.setSourceId(s.getUuid());
            sourceInfo.setSourceName(s.getShowName());
            sourceInfos.add(sourceInfo);
        }
        return sourceInfos;
    }

    @Override
    public List<YearInfo> getYearInfo(int yearId) {
        List<MtimeYearDictT> mtimeYearDictTS = yearDictTService.selectList(null);

        List<YearInfo> yearInfos = new ArrayList<>();
        for (MtimeYearDictT y : mtimeYearDictTS){
            YearInfo yearInfo = new YearInfo();
            if (yearId == y.getUuid()){
                yearInfo.setActive(true);
            }else {
                yearInfo.setActive(false);
            }
            yearInfo.setYearId(y.getUuid());
            yearInfo.setYearName(y.getShowName());
            yearInfos.add(yearInfo);
        }
        return yearInfos;
    }

    //影片查询接口

    //影片详情查询接口

}
