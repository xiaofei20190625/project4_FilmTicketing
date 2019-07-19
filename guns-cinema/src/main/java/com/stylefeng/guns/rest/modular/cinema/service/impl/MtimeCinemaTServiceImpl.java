package com.stylefeng.guns.rest.modular.cinema.service.impl;

import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.modular.cinema.service.IMtimeCinemaTService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 影院信息表 服务实现类
 * </p>
 *
 * @author alexcheng
 * @since 2019-07-16
 */
@Service
public class MtimeCinemaTServiceImpl extends ServiceImpl<MtimeCinemaTMapper, MtimeCinemaT> implements IMtimeCinemaTService {

    @Autowired
    MtimeCinemaTMapper cinemaMapper;
    @Override
    public List<MtimeCinemaT> queryByBrandId(int brandId) {
        return cinemaMapper.queryByBrandId(brandId);
    }
}
