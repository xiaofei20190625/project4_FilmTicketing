package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.gson.Gson;
import com.stylefeng.guns.rest.modular.film.FilmService;
import com.stylefeng.guns.rest.modular.order.dao.*;
import com.stylefeng.guns.rest.modular.order.model.*;
import com.stylefeng.guns.rest.modular.order.util.NumberUtils;
import com.stylefeng.guns.rest.modular.order.util.UUIDUtil;
import com.stylefeng.guns.rest.modular.order.vo.NewOrderVO;
import org.apache.commons.collections.CollectionUtils;
import com.stylefeng.guns.rest.modular.order.dao.MoocOrderTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    MtimeFilmTMapper filmTMapper;
    @Autowired
    MtimeCinemaTMapper cinemaTMapper;
    @Autowired
    MoocOrderTMapper orderMapper;

    private static HashMap<String, String> seatsMap = new HashMap<>();

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
//        HallSeatsInfo hallSeatsInfo = getHallSeatInfo(jsonSeatAddress);
        //由于现阶段前端全部用"4dx.json"实现的，故在这里写成定值
        HallSeatsInfo hallSeatsInfo = getHallSeatInfo("4dx.json");
        String ids = hallSeatsInfo.getIds();
        //将ids拆分为id数组
        Boolean equalSeat = isEqualSeat(ids, seatId);
        return equalSeat;
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
        //--先获取订单表里所有的fieldId匹配的订单list
        EntityWrapper<MoocOrderT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("field_id", field);
        List<MoocOrderT> moocOrderTList = orderTMapper.selectList(entityWrapper);
        //--如果list为空说明还没有该场次的订单信息，则直接返回false
        if (CollectionUtils.isEmpty(moocOrderTList)){
            return false;
        }
        //否则获取所有该场次订单信息中的seats_ids
        String ids = null;
        for (int i = 0; i < moocOrderTList.size(); i++) {
            ids = moocOrderTList.get(i).getSeatsIds();
            Boolean equalSeat = isEqualSeat(ids, seatId);
            //匹配成功，返回true,证明该座位已经被卖出
            if (equalSeat) return true;
        }
        return false;
    }

    @Override
    public NewOrderVO createNewOrder(String fieldId, String soldSeats, String seatsName, Integer userId) {
        NewOrderVO newOrderVO = new NewOrderVO();
        MoocOrderT newOrderT = new MoocOrderT();

        //设置订单id
        String uuid = UUIDUtil.getRandomUUID();
        newOrderVO.setOrderId(uuid);

        //设置订单filmName
        //--通过field_id查询film_id和cinema_id
        MtimeFieldT field = fieldTMapper.selectList(new EntityWrapper<MtimeFieldT>().eq("UUID", fieldId)).get(0);
        Integer filmId = field.getFilmId();
        List<MtimeFilmT> filmTS = filmTMapper.selectList(new EntityWrapper<MtimeFilmT>().eq("UUID", filmId));
        String filmName = filmTS.get(0).getFilmName();
        newOrderVO.setFilmName(filmName);

        //设置订单fieldTime
        String beginTime = field.getBeginTime();
        SimpleDateFormat format1 = new SimpleDateFormat("今天 MM月dd号 ");
        String fieldTime = format1.format(new Date()) + beginTime;
        newOrderVO.setFieldTime(fieldTime);

        //设置订单cinemaName
        Integer cinemaId = field.getCinemaId();
//        NewMtimeCinemaT cinemaT = cinemaTMapper.selectByUUId(cinemaId);
        List<MtimeCinemaT> cinemaTS = cinemaTMapper.selectList(new EntityWrapper<MtimeCinemaT>().eq("UUID", cinemaId));
        String cinemaName = cinemaTS.get(0).getCinemaName();
        newOrderVO.setCinemaName(cinemaName);

        //设置订单seatsName
        String seatsDetailName = getSeatsName(soldSeats);
        newOrderVO.setSeatsName(seatsDetailName);

        //设置订单orderPrice
        String[] ids = soldSeats.split(",");
        int num = ids.length;
        double price = field.getPrice();
        double orderPrice = 1.0 * num * price;
        newOrderVO.setOrderPrice(orderPrice);

        //设置订单orderTimestamp
        String timeStamp = Long.toString(new Date().getTime());
        newOrderVO.setOrderTimestamp(timeStamp);


        newOrderT.setUuid(uuid);
        newOrderT.setCinemaId(cinemaId);
        newOrderT.setFieldId(Integer.parseInt(fieldId));
        newOrderT.setFilmId(filmId);
        newOrderT.setSeatsIds(soldSeats);
        newOrderT.setSeatsName(seatsDetailName);
        newOrderT.setFilmPrice(price);
        newOrderT.setOrderPrice(orderPrice);
        newOrderT.setOrderTime(new Date());
        newOrderT.setOrderUser(userId);
        newOrderT.setOrderStatus(0);

        Integer insert = orderTMapper.insert(newOrderT);
        if (insert != 1){
            return null;
        }

        return newOrderVO;
    }

    /**
     * 判断目标座位号是否和给定的座位号数组匹配
     * @param ids
     * @param targetId
     * @return
     */
    private Boolean isEqualSeat(String ids, String targetId){
        //判空
        if (StringUtils.isEmpty(ids) || StringUtils.isEmpty(targetId)){
            return false;
        }
        //按照逗号将字符串拆分为字符串数组
        String[] idsArr = ids.split(",");
        //将ids数组遍历并录入hashset,以提高代码的执行效率
        HashSet<String> idSet = new HashSet<>();
        for (int i = 0; i < idsArr.length; i++) {
            idSet.add(idsArr[i]);
        }
        //--对目标字符串进行判断，按照其有没有逗号判断是否为座位号数组
        //如果是多个座位号，则将其拆分为id数组（如果不是，则不会拆分）
        String[] targetIds = targetId.split(",");
        //对target数组中的每一个id进行判断
        if (idSet.containsAll(Arrays.asList(targetIds))){
            return true;
        }
        return false;
    }

    /**
     * 根据座位号生成座位信息
     * @param soldSeats
     * @return
     */
    private String getSeatsName(String soldSeats) {
        Jedis jedis = new Jedis();
        //调用getHallSeatInfo方法，得到座位信息
        HallSeatsInfo hallSeatInfo = getHallSeatInfo("4dx.json");
        //将soldSeats转换为ids数组
        String[] ids = soldSeats.split(",");
        //遍历HallSeatsInfo的Single和Couple，并将每一个座位录入到map里面（以座位号为key，排和列为value）
        List<List<Single>> single = hallSeatInfo.getSingle();
        List<List<Couple>> couple = hallSeatInfo.getCouple();

        if (seatsMap.isEmpty()){
            NumberUtils numberUtils = NumberUtils.getInstance();
            for (List<Single> row : single){
                for (Single s : row){
                    seatsMap.put(s.getSeatId().toString(), "第" + numberUtils.formatInteger(s.getRow()) + "排" + "第" + s.getColumn() + "座");
                }
            }
            for (List<Couple> row : couple){
                for (Couple s : row){
                    seatsMap.put(s.getSeatId().toString(), "第" + numberUtils.formatInteger(s.getRow()) + "排" + "第" + s.getColumn() + "座");
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            sb.append(seatsMap.get(ids[i]));
            if (i != ids.length - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 根据json文件的文件名从redis中获取json数据，并封装为JavaBean作为返回值
     * @param jsonSeat
     * @return
     */
    private HallSeatsInfo getHallSeatInfo(String jsonSeat) {
        if (StringUtils.isEmpty(jsonSeat)) {
            return null;
        }
        Jedis jedis = new Jedis();
        String jsonSeats = jedis.get(jsonSeat);
        //将json数据解析为JavaBean
        Gson gson = new Gson();
        HallSeatsInfo hallSeatsInfo = null;
        try {
            hallSeatsInfo = gson.fromJson(jsonSeats, HallSeatsInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hallSeatsInfo;
    }

    @Override
    public String selectSoldSeats(int fieldId) {
        return orderMapper.selectSoldSeatsByFieldId(fieldId);

    }

    @Override
    public NewOrderVO createNewOrder123(String fieldId, String soldSeats, String seatsName, Integer uuid) {
        NewOrderVO newOrderVO = new NewOrderVO();
        MoocOrderT newOrderT = new MoocOrderT();

        //设置订单id
        String uuid1 = UUIDUtil.getRandomUUID();
        newOrderVO.setOrderId(uuid1);

        //设置订单filmName
        //--通过field_id查询film_id和cinema_id
        MtimeFieldT field = fieldTMapper.selectList(new EntityWrapper<MtimeFieldT>().eq("UUID", fieldId)).get(0);
        Integer filmId = field.getFilmId();
        List<MtimeFilmT> filmTS = filmTMapper.selectList(new EntityWrapper<MtimeFilmT>().eq("UUID", filmId));
        String filmName = filmTS.get(0).getFilmName();
        newOrderVO.setFilmName(filmName);

        //设置订单fieldTime
        String beginTime = field.getBeginTime();
        SimpleDateFormat format1 = new SimpleDateFormat("今天 MM月dd号 ");
        String fieldTime = format1.format(new Date()) + beginTime;
        newOrderVO.setFieldTime(fieldTime);

        //设置订单cinemaName
        Integer cinemaId = field.getCinemaId();
//        NewMtimeCinemaT cinemaT = cinemaTMapper.selectByUUId(cinemaId);
        List<MtimeCinemaT> cinemaTS = cinemaTMapper.selectList(new EntityWrapper<MtimeCinemaT>().eq("UUID", cinemaId));
        String cinemaName = cinemaTS.get(0).getCinemaName();
        newOrderVO.setCinemaName(cinemaName);

        //设置订单seatsName
        String seatsDetailName = getSeatsName(soldSeats);
        newOrderVO.setSeatsName(seatsDetailName);

        //设置订单orderPrice
        String[] ids = soldSeats.split(",");
        int num = ids.length;
        double price = field.getPrice();
        double orderPrice = 1.0 * num * price;
        newOrderVO.setOrderPrice(orderPrice);

        //设置订单orderTimestamp
        String timeStamp = Long.toString(new Date().getTime());
        newOrderVO.setOrderTimestamp(timeStamp);


        newOrderT.setUuid(uuid1);
        newOrderT.setCinemaId(cinemaId);
        newOrderT.setFieldId(Integer.parseInt(fieldId));
        newOrderT.setFilmId(filmId);
        newOrderT.setSeatsIds(soldSeats);
        newOrderT.setSeatsName(seatsDetailName);
        newOrderT.setFilmPrice(price);
        newOrderT.setOrderPrice(orderPrice);
        newOrderT.setOrderTime(new Date());
        newOrderT.setOrderUser(uuid);
        newOrderT.setOrderStatus(0);

        Integer insert = orderTMapper.insert(newOrderT);
        if (insert != 1){
            return null;
        }

        return newOrderVO;
    }
}
