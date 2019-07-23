package com.stylefeng.guns.rest.modular.cinema;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.modular.cinema.vo.NewMtimeCinemaT;

import java.util.List;

/**
 * <p>
 * 影院信息表 服务类
 * </p>
 *
 * @author alexcheng
 * @since 2019-07-16
 */
public interface IMtimeCinemaTService {

    List<NewMtimeCinemaT> queryByBrandId(int brandId);

    List<NewMtimeCinemaT> selectList();
}
