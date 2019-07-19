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
    @Autowired
    IMtimeFilmTService iMtimeFilmTService;
    @Autowired
    IMtimeSourceDictTService sourceDictTService;
    @Autowired
    IMtimeCatDictTService catDictTService;
    @Autowired
    IMtimeFilmInfoTService infoTService;
    @Autowired
    MtimeFilmActorTMapper filmActorTMapper;
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
        //info01影片类型
        String info01 = getFilmType(film);
        //info02产地/电影时间
        String info02 = getFilmOriginAndLength(film, filmInfo);
        //info03 上映时间和地点
        String info03 = getFilmDateAndPlace(film, filmInfo);
        //info04
        Info04 info04 = getInfo04(filmInfo);


        filmDetailVO.setFilmId(film.getUuid());
        filmDetailVO.setFilmName(film.getFilmName());
        filmDetailVO.setImgAddress(film.getImgAddress());
        filmDetailVO.setScore(film.getFilmScore());
        filmDetailVO.setFilmEnName(filmInfo.getFilmEnName());
        filmDetailVO.setScoreNum(filmInfo.getFilmScoreNum() + "");
        filmDetailVO.setTotalBox(boxOffice + "");
        filmDetailVO.setInfo01(info01);
        filmDetailVO.setInfo02(info02);
        filmDetailVO.setInfo03(info03);
        filmDetailVO.setInfo04(info04);
        return filmDetailVO;
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

        String filmImgs = filmInfo.getFilmImgs();
        String[] imgs = filmImgs.split(",");
        ImgVO imgVO = new ImgVO();
        imgVO.setMainImg(imgs[0]);
        imgVO.setImg01(imgs[1]);
        imgVO.setImg02(imgs[2]);
        imgVO.setImg03(imgs[3]);
        imgVO.setImg04(imgs[4]);

        filmActors.setActors(actors1);
        info04.setActors(filmActors);
        info04.setBiography(filmInfo.getBiography());
        info04.setFilmId(filmId);
        info04.setImgVO(imgVO);
        return info04;
    }

    private String getFilmDateAndPlace(MtimeFilmT film, MtimeFilmInfoT filmInfo) {
        Integer filmSource = film.getFilmSource();
        MtimeSourceDictT sourceDictT = sourceDictTService.selectById(filmSource);
        StringBuffer info03 = new StringBuffer();
        Date date = film.getFilmTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String filmTime = simpleDateFormat.format(date);
        info03.append(filmTime).append(sourceDictT.getShowName()).append("上映");
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

    /*private String getTotalBox(Integer boxOffice) {
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

    }*/


}
