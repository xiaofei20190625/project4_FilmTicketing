package com.stylefeng.guns.rest.modular.cinema.service;

import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 影院信息表 服务类
 * </p>
 *
 * @author alexcheng
 * @since 2019-07-16
 */
public interface IMtimeCinemaTService extends IService<MtimeCinemaT> {

    List<MtimeCinemaT> queryByBrandId(int brandId);
}
