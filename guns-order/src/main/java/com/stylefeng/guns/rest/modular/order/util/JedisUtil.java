package com.stylefeng.guns.rest.modular.order.util;

import com.stylefeng.guns.rest.config.properties.JedisProperties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/21 Time 17:26
 */
public final class JedisUtil {
    private JedisUtil(){}
    private static JedisPool jedisPool;
    private static int maxTotal;
    private static int maxWaitMillis;
    private static String host;
    private static int port;

    //读取JedisProperties配置文件
    static {
        JedisProperties properties = new JedisProperties();
        maxTotal = Integer.parseInt(properties.getMaxTotal());
        maxWaitMillis = Integer.parseInt(properties.getMaxWaitMillis());
        host = properties.getHost();
        port = Integer.parseInt(properties.getPort());
    }

    //创建连接池
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }

    //获取jedis
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    //关闭jedis
    public static void close(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }
}
