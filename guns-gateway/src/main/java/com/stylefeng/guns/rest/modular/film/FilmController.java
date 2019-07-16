package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.film.VO.CatInfo;
import com.stylefeng.guns.rest.modular.film.VO.ConditionListResult;
import com.stylefeng.guns.rest.modular.film.VO.SourceInfo;
import com.stylefeng.guns.rest.modular.film.VO.YearInfo;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/film")
public class FilmController {



    ///film/getConditionListbrandId

    @RequestMapping("/getConditionList")
    @ResponseBody
    public ConditionListResult getConditionList(int catId, int sourceId, int yearId ){

        //调用服务

         @Reference
         FilmService service;

        /*ArrayList<CatInfo> catInfos= service.getcatInfoByCatId(catId);
        ArrayList<SourceInfo> sourceId= service.getsourceInfoByCatId(catId);
        ArrayList<YearInfo> yearId= service.getyearInfoByCatId(catId);*/


        return null;

    }

}
