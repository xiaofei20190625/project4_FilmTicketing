package com.stylefeng.guns.rest.modular.film;


import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.film.model.GetFilmCondition;
import com.stylefeng.guns.rest.modular.film.vo.FilmDetailVO;
import com.stylefeng.guns.rest.modular.film.vo.GetFilmVO;
import com.stylefeng.guns.rest.modular.film.vo.ResponseVO1;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
public class FilmController {
    @Reference
    FilmService filmService;
    //首页接口：/film/getIndex

    //影片条件列表查询接口：/film/getConditionList

    //影片查询接口：/film/getFilms
    @RequestMapping("getFilms")
    public GetFilmVO getFilms(@RequestBody GetFilmCondition getFilmCondition) {
        GetFilmVO getFilmVO = filmService.getFilmVO(getFilmCondition);
        return getFilmVO;
    }

    //影片详情查询接口：/film/films/{影片编号或影片名称}
    @RequestMapping("films/{searchValue}")
    public ResponseVO1 getFilmDetail(Integer searchType,@PathVariable("searchValue") String searchValue) {
        FilmDetailVO filmDetail = filmService.getFilmDetail(searchType, searchValue);
        ResponseVO1<Object> responseVO1 = new ResponseVO1<>(0, "http://img.meetingshop.cn/", filmDetail);
        return responseVO1;
    }

}
