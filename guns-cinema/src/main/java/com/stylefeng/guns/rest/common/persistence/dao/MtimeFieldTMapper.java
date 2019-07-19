package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author alexcheng
 * @since 2019-07-16
 */
public interface MtimeFieldTMapper extends BaseMapper<MtimeFieldT> {

    List<MtimeFieldT>  selectByCinemaId(@Param("cinemaId") int cinemaId);
}
