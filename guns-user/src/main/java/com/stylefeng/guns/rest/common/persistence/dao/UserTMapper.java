package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.common.persistence.model.UserT;


/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xiaofei
 * @since 2019-07-17
 */
public interface UserTMapper extends BaseMapper<UserT> {

    int selectUser(String userName);

    String queryPasswordByUserName(String userName);
}
