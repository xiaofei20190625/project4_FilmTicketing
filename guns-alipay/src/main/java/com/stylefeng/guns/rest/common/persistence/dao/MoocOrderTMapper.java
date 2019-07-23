package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-17
 */

@Repository
public interface MoocOrderTMapper{

    MoocOrderT selectOneOrder(String uuid);

    Integer updateNew(MoocOrderT orderT);
}
