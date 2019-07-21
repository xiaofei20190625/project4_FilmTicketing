package com.stylefeng.guns.rest.modular.order.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.order.model.MtimeFieldT;
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
