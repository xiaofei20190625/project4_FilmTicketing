package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeHallDictT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallFilmInfoT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影厅电影信息表 Mapper 接口
 * </p>
 *
 * @author alexcheng
 * @since 2019-07-16
 */
public interface MtimeHallFilmInfoTMapper extends BaseMapper<MtimeHallFilmInfoT> {

    MtimeHallFilmInfoT selectByFilmId(@Param("filmId") Integer filmId);
}
