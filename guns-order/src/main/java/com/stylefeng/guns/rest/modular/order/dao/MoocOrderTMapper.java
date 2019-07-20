package com.stylefeng.guns.rest.modular.order.dao;

import com.stylefeng.guns.rest.modular.order.model.MoocOrderT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-17
 */
public interface MoocOrderTMapper extends BaseMapper<MoocOrderT> {

    String selectSoldSeatsByFieldId(@Param("fieldId") int fieldId);
}
