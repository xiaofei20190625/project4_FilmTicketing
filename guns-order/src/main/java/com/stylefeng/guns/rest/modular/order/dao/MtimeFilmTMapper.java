package com.stylefeng.guns.rest.modular.order.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.order.model.MtimeFilmT;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-16
 */
public interface MtimeFilmTMapper extends BaseMapper<MtimeFilmT> {
    MtimeFilmT selectByName(String filmName);
}
