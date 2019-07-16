package com.stylefeng.guns.rest.modular.film;


import com.stylefeng.guns.rest.modular.film.vo.ConditionListResult;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/film")
public class FilmController {

    //首页接口：/film/getIndex

    //影片条件列表查询接口：/film/getConditionList

    //影片查询接口：/film/getFilms

    //影片详情查询接口：/film/films/{影片编号或影片名称}

}
