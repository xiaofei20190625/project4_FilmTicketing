package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.rest.config.properties.JwtProperties;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.order.vo.NewOrderVO;
import com.stylefeng.guns.rest.modular.user.vo.UserInfo;
import com.stylefeng.guns.rest.modular.user.vo.UserResponseVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/18 Time 20:43
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Reference(interfaceClass = OrderService.class)
    OrderService orderService123;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @RequestMapping(value = "buyTickets", params = {"fieldId", "soldSeats", "seatsName"}, method = RequestMethod.POST)
    public ResponseVo buyTickets(String fieldId, String soldSeats, String seatsName, HttpServletRequest request){
        //先判断传来的座位号是否是真实有效的
        /*Boolean isTrueSeats = orderService.isTrueSeats(fieldId, soldSeats);
        if (!isTrueSeats){
            ResponseVo responseVo = ResponseVo.fail("不存在此座位！");
            return responseVo;
        }*/
        //然后判断该座位是否是已经售出，防止重复下单
        Boolean isSoldSeats = orderService123.isSoldSeats(fieldId, soldSeats);
        if (isSoldSeats){
            return ResponseVo.fail("该座位已经被售出");
        }
        //经过上面两层判断后，可以生成该订单
        String authToken = (String)authToken(request);
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(authToken);
        Jedis jedis = new Jedis();
        String userInfo = jedis.get(usernameFromToken);
        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        UserInfo userInfo2 = JSON.toJavaObject(jsonObject , UserInfo.class);
        Integer uuid = userInfo2.getUuid();
        //NewOrderVO newOrder = orderService123.createNewOrder(fieldId, soldSeats, seatsName, uuid);
        NewOrderVO newOrder = orderService123.createNewOrder123(fieldId, soldSeats, seatsName, uuid);
        if (newOrder != null){
            ResponseVo ok = ResponseVo.ok(newOrder, "创建订单成功");
            return ok;
        }
        ResponseVo fail = ResponseVo.fail("创建订单失败");
        return fail;
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
