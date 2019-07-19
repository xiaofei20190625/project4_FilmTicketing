package com.stylefeng.guns.rest.modular.film.dao;

import com.stylefeng.guns.rest.modular.film.model.MtimeActorT;
import com.stylefeng.guns.rest.modular.film.model.MtimeFilmActorT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.film.vo.Actor;

import java.util.List;

/**
 * <p>
 * 影片与演员映射表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-16
 */
public interface MtimeFilmActorTMapper extends BaseMapper<MtimeFilmActorT> {
    Actor selectByAId(Integer actorId);

    List<MtimeFilmActorT> selectByFilmId(Integer filmId);
}
