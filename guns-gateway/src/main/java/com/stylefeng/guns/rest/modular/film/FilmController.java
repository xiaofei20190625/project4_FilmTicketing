package com.stylefeng.guns.rest.modular.film;


import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.film.model.*;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVo;
import com.stylefeng.guns.rest.modular.film.vo.FilmVo;
import com.stylefeng.guns.rest.modular.film.vo.IndexVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    @Reference
    FilmService filmService;

    //首页接口：/film/getIndex
    @RequestMapping("getIndex")
    public IndexVo getIndexFilm(){
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
        FilmVo<HotFilm> hotFilms = filmService.getIndexHotFilms();
        FilmVo<SoonFilm> soonFilms = filmService.getIndexSoonFilms();
        List<BoxRanking> boxRanking = filmService.getIndexBoxRanking();
        List<ExpectRanking> expectRanking = filmService.getIndexExpectRanking();
        List<Top100> top100 = filmService.getIndexTop100s();

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

    //影片查询接口：/film/getFilms

    //影片详情查询接口：/film/films/{影片编号或影片名称}

}
