package com.stylefeng.guns.rest.modular.order.util;

import java.util.UUID;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/20 Time 17:06
 */
public class UUIDUtil {
    public static String getRandomUUID(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
