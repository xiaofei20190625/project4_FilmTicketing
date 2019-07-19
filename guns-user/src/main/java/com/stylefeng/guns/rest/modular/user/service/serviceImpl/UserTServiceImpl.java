package com.stylefeng.guns.rest.modular.user.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.UserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.UserT;
import com.stylefeng.guns.rest.modular.user.service.IUserTService;
import com.stylefeng.guns.rest.modular.user.service.UserService;
import com.stylefeng.guns.rest.modular.user.vo.RegisterUser;
import com.stylefeng.guns.rest.modular.user.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiaofei
 * @since 2019-07-17
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = UserService.class)
public class UserTServiceImpl extends ServiceImpl<UserTMapper, UserT> implements IUserTService,UserService{

    @Autowired
    UserTMapper userTMapper;
    @Override
    public int queryUserByUserName(String userName) {
        int i = userTMapper.selectUser(userName);
        return i;
    }

    @Override
    public int register(RegisterUser registerUser) {
        UserT user = new UserT();
        String username = registerUser.getUsername();
        user.setUserName(username);
        user.setUserPwd(MD5Util.encrypt(registerUser.getPassword()));
        user.setAddress(registerUser.getAddress());
        user.setBeginTime(new Date());
        user.setEmail(registerUser.getEmail());
        return userTMapper.insert(user);
    }

    @Override
    public String queryPasswordByUserName(String userName) {
        return userTMapper.queryPasswordByUserName(userName);
    }

    @Override
    public UserInfo queryUser(String usernameFromToken) {

        UserT user = new UserT();
        user.setUserName(usernameFromToken);
        UserT userT = userTMapper.selectOne(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setUuid(userT.getUuid());
        userInfo.setUsername(userT.getUserName());
        userInfo.setNickname(userT.getNickName());
        userInfo.setEmail(userT.getEmail());
        userInfo.setPhone(userT.getUserPhone());
        userInfo.setSex(userT.getUserSex());
        userInfo.setBirthday(userT.getBirthday());
        userInfo.setLifeState(userT.getLifeState());
        userInfo.setBiography(userT.getBiography());
        userInfo.setAddress(userT.getAddress());
        userInfo.setHeadAddress(userT.getHeadUrl());
        userInfo.setCreateTime(userT.getBeginTime());
        userInfo.setUpdateTime(userT.getUpdateTime());
        return userInfo;
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        UserT userT = new UserT();
        userT.setUuid(          userInfo.getUuid(       )) ;
        userT.setNickName(      userInfo.getNickname(   )) ;
        userT.setEmail(         userInfo.getEmail(      )) ;
        userT.setUserPhone(     userInfo.getPhone(      )) ;
        userT.setUserSex(       userInfo.getSex(        )) ;
        userT.setBirthday(      userInfo.getBirthday(   )) ;
        userT.setLifeState(     userInfo.getLifeState(  )) ;
        userT.setBiography(     userInfo.getBiography(  )) ;
        userT.setAddress(       userInfo.getAddress(    )) ;
        userT.setUpdateTime(    userInfo.getUpdateTime( ));
        return userTMapper.updateById(userT);
    }
}
