package com.stylefeng.guns.rest.modular.film.dao;

import com.stylefeng.guns.rest.modular.film.model.MtimeCatDictT;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 类型信息表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-16
 */
public interface MtimeCatDictTMapper extends BaseMapper<MtimeCatDictT> {
    List<MtimeCatDictT> selectByIds(String[] ids);
}
