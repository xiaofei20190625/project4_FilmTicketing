package com.stylefeng.guns.rest.modular.user.service;


import com.stylefeng.guns.rest.modular.user.vo.RegisterUser;
import com.stylefeng.guns.rest.modular.user.vo.UserInfo;

public interface UserService {
    int queryUserByUserName(String userName);

    int register(RegisterUser user);

    String queryPasswordByUserName(String userName);

    UserInfo queryUser(String usernameFromToken);

    int updateUserInfo(UserInfo userInfo);
}
