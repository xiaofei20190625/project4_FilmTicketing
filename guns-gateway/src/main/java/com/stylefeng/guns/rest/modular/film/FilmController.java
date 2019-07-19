package com.stylefeng.guns.rest.modular.film;


import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.film.model.*;
import com.stylefeng.guns.rest.modular.film.vo.*;
import com.stylefeng.guns.rest.modular.film.vo.ConditionListVo;
import com.stylefeng.guns.rest.modular.film.vo.IndexVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
@Slf4j
public class FilmController {
    @Reference(interfaceClass = FilmService.class, check = true)
    FilmService filmService;

    //首页接口：/film/getIndex
    @RequestMapping(value = "getIndex", method = RequestMethod.GET)
    public Object getIndexFilm(){
        IndexVo indexVo = new IndexVo();
        indexVo.setImgPre("http://img.meetingshop.cn/");
        FilmIndexVo data = getIndexFilmElements();
        indexVo.setData(data);
        if (data == null){
            indexVo.setStatus(1);
        }
        indexVo.setStatus(0);
        return indexVo;
    }

    private FilmIndexVo getIndexFilmElements() {
        List<Banner> banners = filmService.getIndexBanners();
        FilmVo<HotFilm> hotFilms = filmService.getIndexHotFilms(8, true);
        FilmVo<SoonFilm> soonFilms = filmService.getIndexSoonFilms(8, true);
        List<BoxRanking> boxRanking = filmService.getIndexBoxRanking(10);
        List<ExpectRanking> expectRanking = filmService.getIndexExpectRanking(10);
        List<Top100> top100 = filmService.getIndexTop100s(7);

        FilmIndexVo filmIndexVo = new FilmIndexVo();
        filmIndexVo.setBanners(banners);
        filmIndexVo.setHotFilms(hotFilms);
        filmIndexVo.setSoonFilms(soonFilms);
        filmIndexVo.setBoxRanking(boxRanking);
        filmIndexVo.setExpectRanking(expectRanking);
        filmIndexVo.setTop100(top100);

        return filmIndexVo;
    }

    //影片条件列表查询接口：/film/getConditionList
    @RequestMapping(value = "getConditionList", method = RequestMethod.GET)
    public Object getConditionList(int catId, int sourceId, int yearId){
        ConditionListVo conditionListVo = new ConditionListVo();
        conditionListVo.setStatus(0);
        conditionListVo.setMsg("");
        conditionListVo.setImgPre("");
        List<CatInfo> catInfos = filmService.getCatInfo(catId);
        List<SourceInfo> sourceInfos = filmService.getSourceInfo(sourceId);
        List<YearInfo> yearInfos = filmService.getYearInfo(yearId);

        conditionListVo.setCatInfo(catInfos);
        conditionListVo.setSourceInfo(sourceInfos);
        conditionListVo.setYearInfo(yearInfos);
        return conditionListVo;
    }

    //影片查询接口：/film/getFilms

    //影片详情查询接口：/film/films/{影片编号或影片名称}

}
