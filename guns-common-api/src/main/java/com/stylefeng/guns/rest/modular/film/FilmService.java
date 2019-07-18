package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.film.model.*;
import com.stylefeng.guns.rest.modular.film.vo.*;

import java.util.ArrayList;
import java.util.List;

public interface FilmService {
    //首页接口

    //--获取banners
    List<Banner> getIndexBanners();
    //--获取热映影片
    FilmVo<HotFilm> getIndexHotFilms(Integer count, Boolean isLimit);
    //--获取即将上映影片
    FilmVo<SoonFilm> getIndexSoonFilms(Integer count, Boolean isLimit);
    //获取票房排行榜
    List<BoxRanking> getIndexBoxRanking(Integer count);
    //--获取人气排行榜
    List<ExpectRanking> getIndexExpectRanking(Integer count);
    //--获取top100
    List<Top100> getIndexTop100s(Integer count);

    //影片条件列表查询接口
    List<YearInfo> getYearInfo(int yearId);
    List<CatInfo> getCatInfo(int catId);
    List<SourceInfo> getSourceInfo(int sourceId);

    //影片查询接口

    //影片详情查询接口

}
