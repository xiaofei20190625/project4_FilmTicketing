package com.stylefeng.guns.rest.modular.film.dao;

import com.stylefeng.guns.rest.modular.film.model.MtimeFilmInfoT;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-16
 */
public interface MtimeFilmInfoTMapper extends BaseMapper<MtimeFilmInfoT> {
    MtimeFilmInfoT selectByFilmId(String filmId);
}
