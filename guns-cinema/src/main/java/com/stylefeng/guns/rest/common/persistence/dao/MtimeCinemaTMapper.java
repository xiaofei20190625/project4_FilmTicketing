package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 影院信息表 Mapper 接口
 * </p>
 *
 * @author alexcheng
 * @since 2019-07-16
 */
public interface MtimeCinemaTMapper extends BaseMapper<MtimeCinemaT> {

    List<MtimeCinemaT> queryByBrandId(@Param("brandId") int brandId);

    MtimeCinemaT selectByUUId(@Param("cinemaId") int cinemaId);
}
