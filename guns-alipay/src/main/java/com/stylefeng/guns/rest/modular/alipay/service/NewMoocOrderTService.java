package com.stylefeng.guns.rest.modular.alipay.service;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;

/**
 * <p>
 * 订单信息表 服务类
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-17
 */
public interface NewMoocOrderTService {

    MoocOrderT selectOneOrder(String uuid);

    Boolean updateNew(MoocOrderT orderT);
}
