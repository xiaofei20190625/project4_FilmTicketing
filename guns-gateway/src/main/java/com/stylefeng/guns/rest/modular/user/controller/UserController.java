package com.stylefeng.guns.rest.modular.user.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.config.properties.JwtProperties;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.vo.AuthResponse;
import com.stylefeng.guns.rest.modular.user.service.UserService;
import com.stylefeng.guns.rest.modular.user.vo.RegisterUser;
import com.stylefeng.guns.rest.modular.user.vo.UserInfo;
import com.stylefeng.guns.rest.modular.user.vo.UserResponseVO;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaofei
 * @since 2019-07-16
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Reference(interfaceClass = UserService.class)
    UserService userService;
    //user/register
    @PostMapping("/register")
    @ResponseBody
    public UserResponseVO register(@RequestBody RegisterUser user){

        int userName = userService.queryUserByUserName(user.getUsername());
        if (userName>=1){
            return UserResponseVO.exist();
        }
        int insert = userService.register(user);
        if (insert==1&&userName==0){
            return UserResponseVO.ok();
        }
        return UserResponseVO.fail();

    }

    /*2、	用户名验证接口
        	请求地址
        /user/check
        	请求方式
            post*/

    @PostMapping("/check")
    @ResponseBody
    public UserResponseVO check(@RequestBody String username){

        int userName = userService.queryUserByUserName(username);

        if (userName==1){
            return UserResponseVO.exist();
        }
        if (userName==0){
            return UserResponseVO.verify();
        }

        return UserResponseVO.fail();

    }
    /*3、	用户登陆接口在AuthController
        	请求地址
        /auth
        	请求方式
        post
        */
   /* 5、	用户信息查询接口
        	请求地址
        /user/getUserInfo
        	请求方式
                    get
        	请求字段
            无，但是要带上Authorization的头信息*/
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("/getUserInfo")
    @ResponseBody
    public Object getUserInfo(HttpServletRequest request){
        String authToken = (String) authToken(request);
        try{
            String usernameFromToken = jwtTokenUtil.getUsernameFromToken(authToken);
            UserInfo user = userService.queryUser(usernameFromToken);
            return AuthResponse.ok(user);
        }catch (Exception e){
            return UserResponseVO.fail();
        }

    }

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

    //user/updateUserInfo
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public Object updateUserInfo(@RequestBody UserInfo userInfo){
        try {
            userInfo.setUpdateTime(new Date());
            int updateUserInfo = userService.updateUserInfo(userInfo);
            if (updateUserInfo==1){
                return AuthResponse.ok(userInfo);
            }
            return UserResponseVO.fail(1,"用户信息修改失败");
        }catch (Exception e){
            return UserResponseVO.fail();
        }
    }
/*    4、	用户退出接口
        	请求地址
        /user/logout
        	请求方式
                    get*/
    @GetMapping("/logout")
    @ResponseBody
    public Object logout(HttpServletRequest request){
        String authToken = (String) authToken(request);
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(authToken);
        if (usernameFromToken==null){
            return UserResponseVO.ok(1,"退出失败，用户尚未登陆");
        }
        try{
            //待加入退出逻辑
            return UserResponseVO.ok(0,"退出成功");
        }catch (Exception e){
            return UserResponseVO.fail();
        }

    }
}

