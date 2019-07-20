package com.stylefeng.guns.rest.modular.order.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.config.properties.JwtProperties;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaInfo;
import com.stylefeng.guns.rest.modular.film.FilmService;
import com.stylefeng.guns.rest.modular.film.model.GetFilmCondition;
import com.stylefeng.guns.rest.modular.order.service.IOrderTService;
import com.stylefeng.guns.rest.modular.order.vo.NewOrderTInfo;
import com.stylefeng.guns.rest.modular.order.vo.OrderResponseVO;
import com.stylefeng.guns.rest.modular.user.vo.UserInfo;
import com.stylefeng.guns.rest.modular.user.vo.UserResponseVO;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author xiaofei
 * @since 2019-07-19
 */
@Controller
@RequestMapping("/order")
public class OrderTController {

    /*2、	获取用户订单信息接口
                	请求地址
                 /order/getOrderInfo
                	请求方式
                post , 需携带JWT信息
                */
    @Reference(interfaceClass = IOrderTService.class)
    IOrderTService iOrderTService;
    @Reference(interfaceClass = CinemaServiceAPI.class, check = false)
    CinemaServiceAPI cinemaService;
    @Reference(interfaceClass = FilmService.class, check = true)
    FilmService filmService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @PostMapping("/getOrderInfo")
    @ResponseBody
    public Object getOrderInfo(@RequestParam(defaultValue = "1") int nowPage,
                               @RequestParam(defaultValue = "5") int pageSize,
                               HttpServletRequest request){
        try{

            String authToken = (String)authToken(request);
            String usernameFromToken = jwtTokenUtil.getUsernameFromToken(authToken);
            Jedis jedis = new Jedis();
             String userInfo = jedis.get(usernameFromToken);
            JSONObject jsonObject = JSONObject.parseObject(userInfo);
            UserInfo userInfo2 = JSON.toJavaObject(jsonObject , UserInfo.class);
            Integer uuid = userInfo2.getUuid();
            List<NewOrderTInfo> newOrderTInfos = iOrderTService.getOrderInfoPage(uuid);
            for (NewOrderTInfo newOrderTInfo : newOrderTInfos) {
                Integer filmNameId = Integer.valueOf(newOrderTInfo.getFilmName());
                String filmName = filmService.getFilmById(filmNameId);
                newOrderTInfo.setFilmName(filmName);
                Integer cinemaNameId = Integer.valueOf(newOrderTInfo.getCinemaName());
                CinemaInfo cinemaInfo = cinemaService.getCinemaInfoById(cinemaNameId);
                String cinemaName = cinemaInfo.getCinemaName();
                newOrderTInfo.setCinemaName(cinemaName);
            }

            if (newOrderTInfos.size()==0){
                return UserResponseVO.fail(1,"订单列表为空哦！~");
            }
            Page<NewOrderTInfo> newOrderTInfoPage = new Page<>(nowPage, pageSize);
            newOrderTInfoPage.setRecords(newOrderTInfos);
            //PageInfoBT<NewOrderTInfo> newOrderTInfoPageInfoBT = new PageInfoBT<>(newOrderTInfoPage);
            return OrderResponseVO.ok(newOrderTInfoPage);
        }catch (Exception e){
            return UserResponseVO.fail();
        }
    }

    @Autowired
    JwtProperties jwtProperties;

    private Object authToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);

            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                if (flag) {

                    return UserResponseVO.fail(1,"查询失败，用户尚未登陆");
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                return UserResponseVO.fail(1,"查询失败，用户尚未登陆");
            }
        } else {
            //header没有带Bearer字段
            return UserResponseVO.fail(1,"查询失败，用户尚未登陆");
        }
        return authToken;
    }
}

