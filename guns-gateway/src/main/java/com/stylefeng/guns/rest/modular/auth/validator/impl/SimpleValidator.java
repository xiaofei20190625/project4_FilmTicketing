package com.stylefeng.guns.rest.modular.auth.validator.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Credence;
import com.stylefeng.guns.rest.modular.user.service.UserService;
import com.stylefeng.guns.rest.modular.user.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * 直接验证账号密码是不是admin
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
@Service
public class SimpleValidator implements IReqValidator {

    @Reference(interfaceClass = UserService.class)
    UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean validate(Credence credence) {

        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();
        String encrypt = MD5Util.encrypt(password);
        String PASSWORD = userService.queryPasswordByUserName(userName);
        if ( PASSWORD.equals(encrypt)) {
            Jedis jedis = new Jedis();
            UserInfo userInfo = userService.queryUser(userName);
            String userInfoJson =JSON.toJSONString(userInfo);
            jedis.set(userName,userInfoJson);
            jedis.expire(userName,1800);
            return true;
        } else {
            return false;
        }
    }


}
