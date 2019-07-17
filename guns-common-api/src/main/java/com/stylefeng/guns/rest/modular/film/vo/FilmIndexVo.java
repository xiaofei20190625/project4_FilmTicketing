package com.stylefeng.guns.rest.modular.film.vo;

import com.stylefeng.guns.rest.modular.film.model.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/15 Time 22:19
 */
public class FilmIndexVo implements Serializable {
    private List<Banner> banners;

    private FilmVo<HotFilm> hotFilms;

    private FilmVo<SoonFilm> soonFilms;

    private List<BoxRanking> boxRanking;

    private List<ExpectRanking> expectRanking;

    private List<Top100> top100;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public FilmVo<HotFilm> getHotFilms() {
        return hotFilms;
    }

    public void setHotFilms(FilmVo<HotFilm> hotFilms) {
        this.hotFilms = hotFilms;
    }

    public FilmVo<SoonFilm> getSoonFilms() {
        return soonFilms;
    }

    public void setSoonFilms(FilmVo<SoonFilm> soonFilms) {
        this.soonFilms = soonFilms;
    }

    public List<BoxRanking> getBoxRanking() {
        return boxRanking;
    }

    public void setBoxRanking(List<BoxRanking> boxRanking) {
        this.boxRanking = boxRanking;
    }

    public List<ExpectRanking> getExpectRanking() {
        return expectRanking;
    }

    public void setExpectRanking(List<ExpectRanking> expectRanking) {
        this.expectRanking = expectRanking;
    }

    public List<Top100> getTop100() {
        return top100;
    }

    public void setTop100(List<Top100> top100) {
        this.top100 = top100;
    }

    @Override
    public String toString() {
        return "FilmIndexVo{" +
                "banners=" + banners +
                ", hotFilms=" + hotFilms +
                ", soonFilms=" + soonFilms +
                ", boxRanking=" + boxRanking +
                ", expectRanking=" + expectRanking +
                ", top100=" + top100 +
                '}';
    }
}
