package com.stylefeng.guns.rest.modular.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.auth.vo.AuthResponse;
import com.stylefeng.guns.rest.modular.user.vo.UserInfo;
import com.stylefeng.guns.rest.modular.user.vo.UserResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "simpleValidator")
    private IReqValidator reqValidator;

    @PostMapping(value = "${jwt.auth-path}")
    public Object createAuthenticationToken( AuthRequest authRequest) {
        try{
            boolean validate = reqValidator.validate(authRequest);

            if (validate) {
                HashMap<Object, Object> data = new HashMap<>();
                final String randomKey = jwtTokenUtil.getRandomKey();
                final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
                data.put("randomKey",randomKey);
                data.put("token",token);
                return AuthResponse.ok(data);
            } else {
                return UserResponseVO.fail(1,"用户名或密码错误");
            }
        }catch (Exception e){
            return UserResponseVO.fail(999,"系统出现异常，请联系管理员");
        }
    }
}
