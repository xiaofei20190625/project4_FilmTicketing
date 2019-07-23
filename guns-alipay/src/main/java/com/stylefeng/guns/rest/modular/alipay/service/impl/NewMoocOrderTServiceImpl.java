package com.stylefeng.guns.rest.modular.alipay.service.impl;



import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.common.persistence.dao.MoocOrderTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.stylefeng.guns.rest.modular.alipay.service.NewMoocOrderTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-17
 */
@Service
public class NewMoocOrderTServiceImpl implements NewMoocOrderTService {
    @Autowired
    MoocOrderTMapper moocOrderTMapper;
    @Override
    public MoocOrderT selectOneOrder(String uuid) {
        try{
            MoocOrderT moocOrderT = moocOrderTMapper.selectOneOrder(uuid);
            return moocOrderT;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Boolean updateNew(MoocOrderT orderT) {
        Integer update = moocOrderTMapper.updateNew(orderT);
        if (update==1){
            return true;
        }
        return false;
    }

}
