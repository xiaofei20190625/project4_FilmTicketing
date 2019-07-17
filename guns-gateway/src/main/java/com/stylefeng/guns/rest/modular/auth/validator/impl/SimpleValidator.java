package com.stylefeng.guns.rest.modular.auth.validator.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.auth.validator.dto.Credence;
import com.stylefeng.guns.rest.modular.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean validate(Credence credence) {

        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();
        String encrypt = MD5Util.encrypt(password);
        String PASSWORD = userService.queryPasswordByUserName(userName);
        if ( PASSWORD.equals(encrypt)) {
            return true;
        } else {
            return false;
        }
    }
}
