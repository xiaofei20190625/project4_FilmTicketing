package com.stylefeng.guns.rest.modular.cinema.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.modular.cinema.IMtimeCinemaTService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.modular.cinema.vo.NewMtimeCinemaT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@com.alibaba.dubbo.config.annotation.Service(interfaceClass =IMtimeCinemaTService.class )
public class MtimeCinemaTServiceImpl extends ServiceImpl<MtimeCinemaTMapper, MtimeCinemaT> implements IMtimeCinemaTService {

    @Autowired
    MtimeCinemaTMapper cinemaMapper;
    @Override
    public List<NewMtimeCinemaT> queryByBrandId(int brandId) {
        List<MtimeCinemaT> mtimeCinemaTS = cinemaMapper.queryByBrandId(brandId);
        ArrayList<NewMtimeCinemaT> mtimeCinemaTS1 = new ArrayList<>();

        for (MtimeCinemaT mtimeCinemaT : mtimeCinemaTS) {
            NewMtimeCinemaT newMtimeCinemaT = new NewMtimeCinemaT();
            newMtimeCinemaT.setAreaId(mtimeCinemaT.getAreaId());
            newMtimeCinemaT.setBrandId(mtimeCinemaT.getBrandId());
            newMtimeCinemaT.setCinemaAddress(mtimeCinemaT.getCinemaAddress());
            newMtimeCinemaT.setCinemaName(mtimeCinemaT.getCinemaName());
            newMtimeCinemaT.setCinemaPhone(mtimeCinemaT.getCinemaPhone());
            newMtimeCinemaT.setHallIds(mtimeCinemaT.getHallIds());
            newMtimeCinemaT.setImgAddress(mtimeCinemaT.getImgAddress());
            newMtimeCinemaT.setMinimumPrice(mtimeCinemaT.getMinimumPrice());
            newMtimeCinemaT.setUuid(mtimeCinemaT.getUuid());
            mtimeCinemaTS1.add(newMtimeCinemaT);
        }
        return mtimeCinemaTS1;
    }

    @Override
    public List<NewMtimeCinemaT> selectList() {
        List<MtimeCinemaT> mtimeCinemaTS = cinemaMapper.selectList(new EntityWrapper<>());
        ArrayList<NewMtimeCinemaT> mtimeCinemaTS1 = new ArrayList<>();

        for (MtimeCinemaT mtimeCinemaT : mtimeCinemaTS) {
            NewMtimeCinemaT newMtimeCinemaT = new NewMtimeCinemaT();
            newMtimeCinemaT.setAreaId(mtimeCinemaT.getAreaId());
            newMtimeCinemaT.setBrandId(mtimeCinemaT.getBrandId());
            newMtimeCinemaT.setCinemaAddress(mtimeCinemaT.getCinemaAddress());
            newMtimeCinemaT.setCinemaName(mtimeCinemaT.getCinemaName());
            newMtimeCinemaT.setCinemaPhone(mtimeCinemaT.getCinemaPhone());
            newMtimeCinemaT.setHallIds(mtimeCinemaT.getHallIds());
            newMtimeCinemaT.setImgAddress(mtimeCinemaT.getImgAddress());
            newMtimeCinemaT.setMinimumPrice(mtimeCinemaT.getMinimumPrice());
            newMtimeCinemaT.setUuid(mtimeCinemaT.getUuid());
            mtimeCinemaTS1.add(newMtimeCinemaT);
        }
        return mtimeCinemaTS1;
    }
}
