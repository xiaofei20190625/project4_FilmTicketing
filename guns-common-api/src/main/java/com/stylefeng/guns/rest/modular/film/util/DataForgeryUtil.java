package com.stylefeng.guns.rest.modular.film.util;

import java.util.Date;
import java.util.Random;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/17 Time 15:27
 */
//此工具类用来伪造数据
public class DataForgeryUtil {
    private static final int MIN = 10000;

    private static final int MAX = 9999999;

    //此方法用来获取一个随机的电影的想看人数
    public static int getRandomExpectNum(){
        Random random = new Random();
        int expectNum = random.nextInt(MAX) % (MAX - MIN + 1) + MIN;
        return expectNum;
    }

    //此方法用来获取一个随机（向后一个月内）的上映时间
    public static Date getRandomShowTime(){
        Random random = new Random();
        int seconds = random.nextInt(30 * 24 * 60 * 60);
        long milis = 1000L * seconds + new Date().getTime();
        Date showTime = new Date(milis);
        return showTime;
    }
}
