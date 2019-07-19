package com.stylefeng.guns.rest.modular.film;

import com.alibaba.druid.support.monitor.annotation.MField;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.modular.film.FilmService;
import com.stylefeng.guns.rest.modular.film.dao.*;
import com.stylefeng.guns.rest.modular.film.model.*;
import com.stylefeng.guns.rest.modular.film.service.*;
import com.stylefeng.guns.rest.modular.film.util.DataForgeryUtil;
import com.stylefeng.guns.rest.modular.film.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/16 Time 18:16
 */
@Component
@Service(interfaceClass = FilmService.class)
public class FilmServiceImpl implements FilmService {
//    @Autowired
//    private IMtimeBannerTService bannerTService;
//    @Autowired
//    private IMtimeFilmTService filmTService;
//    @Autowired
//    private IMtimeFilmInfoTService filmInfoTService;
//    @Autowired
//    private IMtimeCatDictTService catDictTService;
//    @Autowired
//    private IMtimeSourceDictTService sourceDictTService;
//    @Autowired
//    private IMtimeYearDictTService yearDictTService;

    @Autowired
    private MtimeBannerTMapper bannerTMapper;
    @Autowired
    private MtimeFilmTMapper filmTMapper;
    @Autowired
    private MtimeFilmInfoTMapper filmInfoTMapper;
    @Autowired
    private MtimeCatDictTMapper catDictTMapper;
    @Autowired
    private MtimeSourceDictTMapper sourceDictTMapper;
    @Autowired
    private MtimeYearDictTMapper yearDictTMapper;
    //首页接口
    //--获取首页banner信息
    @Override
    public List<Banner> getIndexBanners() {
        //新建一个首页用的bannerList
        List<Banner> banners = new ArrayList<>();
        //new一个条件构造器
        EntityWrapper<MtimeBannerT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_valid", 1);
        List<MtimeBannerT> mtimeBannerTS = bannerTMapper.selectList(entityWrapper);
        //对查到的bannerList进行判断，如果没有结果，直接返回
        if (CollectionUtils.isEmpty(mtimeBannerTS)){
            return banners;
        }
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

    //--获取首页热映电影--影片状态,1-正在热映，2-即将上映，3-经典影片
    @Override
    public FilmVo<HotFilm> getIndexHotFilms(Integer count, Boolean isLimit) {
        //创建一个新的list
        FilmVo<HotFilm> hotFilmVo = new FilmVo<>();
        List<HotFilm> hotFilms = new ArrayList<>();
        //限制查询条件：只查出所有热映影片(film_status为1的影片)
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", 1);
        int hotFilmAmount = filmTMapper.selectCount(entityWrapper);

        List<MtimeFilmT> mtimeFilmTList = null;
        if (isLimit){
            Page page = new Page(1, count);
            mtimeFilmTList = filmTMapper.selectPage(page, entityWrapper);
        } else {
            mtimeFilmTList = filmTMapper.selectList(entityWrapper);
        }
        //查询数据库
        //如果没有符合条件的影片信息，则直接返回
        if(CollectionUtils.isEmpty(mtimeFilmTList)){
            hotFilmVo.setFilmNum(0);
            return hotFilmVo;
        }
        for (MtimeFilmT filmT : mtimeFilmTList){
            HotFilm hotFilm = new HotFilm();
            hotFilm.setFilmId(filmT.getUuid());
            hotFilm.setFilmName(filmT.getFilmName());
            hotFilm.setFilmType(filmT.getFilmType());
            hotFilm.setFilmScore(filmT.getFilmScore());
            hotFilm.setImgAddress(filmT.getImgAddress());
            hotFilms.add(hotFilm);
        }

        hotFilmVo.setFilmNum(hotFilmAmount);
        hotFilmVo.setFilmInfo(hotFilms);
        return hotFilmVo;
    }

    //--获取首页即将上映的影片信息，film_status为2
    @Override
    public FilmVo<SoonFilm> getIndexSoonFilms(Integer count, Boolean isLimit) {
        //新建list
        FilmVo<SoonFilm> soonFilmVo = new FilmVo<>();
        List<SoonFilm> soonFilms = new ArrayList<>();
        //条件构造器
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", 2);
        int soonFilmAmount = filmTMapper.selectCount(entityWrapper);

        List<MtimeFilmT> mtimeFilmTList = null;
        if (isLimit){
            Page page = new Page(1, count);
            mtimeFilmTList = filmTMapper.selectPage(page, entityWrapper);
        } else {
            mtimeFilmTList = filmTMapper.selectList(entityWrapper);
        }
        if (CollectionUtils.isEmpty(mtimeFilmTList)){
            soonFilmVo.setFilmNum(0);
            return soonFilmVo;
        }
        //循环遍历赋值
        for (MtimeFilmT filmT : mtimeFilmTList){
            SoonFilm soonFilm = new SoonFilm();
            soonFilm.setFilmId(filmT.getUuid());
            soonFilm.setFilmType(filmT.getFilmType());
            soonFilm.setImgAddress(filmT.getImgAddress());
            soonFilm.setFilmName(filmT.getFilmName());
            soonFilm.setExpectNum(DataForgeryUtil.getRandomExpectNum());
            soonFilm.setShowTime(DataForgeryUtil.getRandomShowTime());
            soonFilms.add(soonFilm);
        }
        soonFilmVo.setFilmNum(soonFilmAmount);
        soonFilmVo.setFilmInfo(soonFilms);
        return soonFilmVo;
    }

    //--获取首页票房排行信息
    @Override
    public List<BoxRanking> getIndexBoxRanking(Integer count) {
        List<BoxRanking> boxRankings = new ArrayList<>();
        Page<MtimeFilmT> page = new Page<MtimeFilmT>(1, count, "film_box_office", false);
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        List<MtimeFilmT> mtimeFilmTList = filmTMapper.selectPage(page, entityWrapper);
        if (CollectionUtils.isEmpty(mtimeFilmTList)){
            return boxRankings;
        }
        for (MtimeFilmT filmT : mtimeFilmTList){
            BoxRanking boxRanking = new BoxRanking();
            boxRanking.setFilmId(filmT.getUuid());
            boxRanking.setImgAddress(filmT.getImgAddress());
            boxRanking.setFilmName(filmT.getFilmName());
            boxRanking.setBoxNum(filmT.getFilmBoxOffice());
            boxRankings.add(boxRanking);
        }
        return boxRankings;
    }
    //--获取首页的最受期待排行榜
    @Override
    public List<ExpectRanking> getIndexExpectRanking(Integer count) {
        List<ExpectRanking> expectRankings = new ArrayList<>();
        Page<MtimeFilmT> page = new Page<>(1, count, "film_preSaleNum", false);
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        List<MtimeFilmT> mtimeFilmTList = filmTMapper.selectPage(page, entityWrapper);
        if (CollectionUtils.isEmpty(mtimeFilmTList)){
            return expectRankings;
        }
        for (MtimeFilmT filmT : mtimeFilmTList){
            ExpectRanking expectRanking = new ExpectRanking();
            expectRanking.setFilmId(filmT.getUuid());
            expectRanking.setImgAddress(filmT.getImgAddress());
            expectRanking.setFilmName(filmT.getFilmName());
            expectRanking.setExpectNum(DataForgeryUtil.getRandomExpectNum());
            expectRankings.add(expectRanking);
        }
        return expectRankings;
    }

    //--获取首页的top100电影排行榜
    @Override
    public List<Top100> getIndexTop100s(Integer count) {
        List<Top100> top100s = new ArrayList<>();
        Page<MtimeFilmT> page = new Page<>(1, count, "film_score", false);
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<MtimeFilmT>();
        List<MtimeFilmT> mtimeFilmInfoTList = filmTMapper.selectPage(page, entityWrapper);
        if (CollectionUtils.isEmpty(mtimeFilmInfoTList)){
            return top100s;
        }
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

    //获取电影的导航分类信息
    //--按照电影的类型查询
    @Override
    public List<CatInfo> getCatInfo(int catId) {
        List<MtimeCatDictT> mtimeCatDictTS = catDictTMapper.selectList(null);
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

    //--按照电影的来源查询
    @Override
    public List<SourceInfo> getSourceInfo(int sourceId) {
        List<MtimeSourceDictT> mtimeSourceDictTS = sourceDictTMapper.selectList(null);

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

    //--按照电影的年代划分
    @Override
    public List<YearInfo> getYearInfo(int yearId) {
        List<MtimeYearDictT> mtimeYearDictTS = yearDictTMapper.selectList(null);

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
