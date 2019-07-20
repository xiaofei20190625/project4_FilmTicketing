package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.film.model.GetFilmCondition;
import com.stylefeng.guns.rest.modular.film.model.*;
import com.stylefeng.guns.rest.modular.film.vo.*;

import java.util.ArrayList;
import java.util.List;

public interface FilmService {
    //首页接口
    /**
     * 获取首页banners
     * @return
     */
    List<Banner> getIndexBanners();

    /**
     * 获取首页热映影片
     * @param count
     * @param isLimit
     * @return
     */
    FilmVo<HotFilm> getIndexHotFilms(Integer count, Boolean isLimit);

    /**
     * 获取即将上映影片
     * @param count
     * @param isLimit
     * @return
     */
    FilmVo<SoonFilm> getIndexSoonFilms(Integer count, Boolean isLimit);

    /**
     * 获取票房排行榜
     * @param count
     * @return
     */
    List<BoxRanking> getIndexBoxRanking(Integer count);

    /**
     * 获取人气排行榜
     * @param count
     * @return
     */
    List<ExpectRanking> getIndexExpectRanking(Integer count);

    /**
     * 获取top100
     * @param count
     * @return
     */
    List<Top100> getIndexTop100s(Integer count);

    //影片条件列表查询接口

    /**
     * 查询电影分类信息
     * @param catId
     * @return
     */
    List<CatInfo> getCatInfo(int catId);
    /**
     * 查询电影年份信息
     * @param yearId
     * @return
     */
    List<YearInfo> getYearInfo(int yearId);

    /**
     * 查询电影来源信息
     * @param sourceId
     * @return
     */
    List<SourceInfo> getSourceInfo(int sourceId);

    //影片查询接口
    GetFilmVO getFilmVO(GetFilmCondition getFilmCondition);

    //影片详情查询接口
    FilmDetailVO getFilmDetail(Integer searchType , String searchValue);


    String getFilmById(Integer filmNameId);
}
