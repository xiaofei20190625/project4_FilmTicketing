package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.film.vo.CatInfo;
import com.stylefeng.guns.rest.modular.film.vo.SourceInfo;
import com.stylefeng.guns.rest.modular.film.vo.YearInfo;

import java.util.ArrayList;

public interface FilmService {

    ArrayList<CatInfo> getcatInfoByCatId(int catId);
    ArrayList<SourceInfo>  getsourceInfoByCatId(int catId);
    ArrayList<YearInfo> getyearInfoByCatId(int catId);
}
