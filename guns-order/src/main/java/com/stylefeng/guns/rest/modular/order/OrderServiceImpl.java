package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.gson.Gson;
import com.stylefeng.guns.rest.modular.order.dao.MoocOrderTMapper;
import com.stylefeng.guns.rest.modular.order.dao.MtimeFieldTMapper;
import com.stylefeng.guns.rest.modular.order.dao.MtimeHallDictTMapper;
import com.stylefeng.guns.rest.modular.order.model.HallSeatsInfo;
import com.stylefeng.guns.rest.modular.order.model.MoocOrderT;
import com.stylefeng.guns.rest.modular.order.model.MtimeFieldT;
import com.stylefeng.guns.rest.modular.order.model.MtimeHallDictT;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/18 Time 20:46
 */
@Component
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    MtimeFieldTMapper fieldTMapper;
    @Autowired
    MtimeHallDictTMapper hallDictTMapper;
    @Autowired
    MoocOrderTMapper orderTMapper;

    /**
     * 判断座位信息是否有效
     * @param fieldId
     * @param seatId
     * @return
     */
    @Override
    public Boolean isTrueSeats(String fieldId, String seatId) {
        //根据fieldId获取hallId
        EntityWrapper<MtimeFieldT> entityWrapper1 = new EntityWrapper<>();
        entityWrapper1.eq("uuid", fieldId);
        List<MtimeFieldT> fieldTList = fieldTMapper.selectList(entityWrapper1);
        if (CollectionUtils.isEmpty(fieldTList)){
            return false;
        }
        Integer hallId = fieldTList.get(0).getHallId();
        //根据hallId获取seatAddress
        EntityWrapper<MtimeHallDictT> entityWrapper2 = new EntityWrapper<>();
        entityWrapper2.eq("uuid", hallId);
        List<MtimeHallDictT> hallDictTList = hallDictTMapper.selectList(entityWrapper2);
        if (CollectionUtils.isEmpty(hallDictTList)){
            return false;
        }
        String jsonSeatAddress = hallDictTList.get(0).getSeatAddress();
        Jedis jedis = new Jedis();
        //由于现阶段前端全部用"4dx.json"实现的，故在这里写成定值
        String jsonSeats = jedis.get("4dx.json");

        //将json数据解析为JavaBean
        Gson gson = new Gson();
        HallSeatsInfo hallSeatsInfo = null;
        try{
            hallSeatsInfo = gson.fromJson(jsonSeats, HallSeatsInfo.class);
//            JSONObject jsonObject = JSONObject.parseObject(jsonSeats);
//            hallSeatsInfo = JSON.toJavaObject(jsonObject, HallSeatsInfo.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        String ids = hallSeatsInfo.getIds();
        //将ids拆分为id数组
        String[] idArr = ids.split(",");
        //遍历数组查找是否有该座位号
        for (int i = 0; i < idArr.length; i++) {
            if (seatId.equals(idArr[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断座位是否已经卖出
     * @param field
     * @param seatId
     * @return
     */
    @Override
    public Boolean isSoldSeats(String field, String seatId) {
        //判断座位是否已经售出，需要根据传入的场次信息，去订单表里查询该场次已经售出的所有订单
        //先获取订单表里所有的fieldId匹配的订单list
        EntityWrapper<MoocOrderT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("field_id", field);
        List<MoocOrderT> moocOrderTList = orderTMapper.selectList(entityWrapper);
        return null;
    }
}
