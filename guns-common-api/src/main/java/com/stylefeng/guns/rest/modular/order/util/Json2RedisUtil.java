package com.stylefeng.guns.rest.modular.order.util;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/19 Time 19:25
 */
public class Json2RedisUtil {
    /**
     * 此方法使用io流读取json文件，
     * 将内容转换为json字符串，
     * 然后按照文件名录入redis数据库
     */
    public static void inputJson2Redis(File jsonFile) throws IOException {
        //判断文件是否存在
        if (!jsonFile.exists() || jsonFile.isDirectory() || !jsonFile.getName().endsWith(".json")){
            return;
        }
        //新建一个StringBuilder,用来拼接json字符串
        StringBuilder sb = new StringBuilder();
        //io流读取json文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile));
        //将内容拼接为字符串
        String s = new String();
        while((s = bufferedReader.readLine()) != null){
            sb.append(s);
        }
        //关流
        bufferedReader.close();
        //将("文件名","字符串")录入redis数据库
        String key = jsonFile.getName();
        String value = sb.toString();
        Jedis jedis = new Jedis();
        String msg = jedis.set(key, value);
        System.out.println("input jsonfile " + jsonFile.getName() + " to Redis:" + msg);
    }

    //将一个文件夹内的所有json文件转换为字符串录入redis数据库
    public static void inputJsonFiles2Redis(File jsonDir) throws IOException {
        //判断文件夹是否存在
        if (!jsonDir.exists() || !jsonDir.isDirectory()){
            return;
        }
        //获取该文件夹下的json文件数组
        File[] jsonFiles = jsonDir.listFiles();
        for (int i = 0; i < jsonFiles.length; i++) {
            //遍历数组，并判断是否为json文件，如果是则调用inputJson2Redis方法
            if (jsonFiles[i].isFile() && jsonFiles[i].getName().endsWith(".json")){
                inputJson2Redis(jsonFiles[i]);
            }
        }
    }

    /*public static void main(String[] args) throws IOException {
        File file = new File("D:\\14期课件\\project4\\前端代码\\film-front3.0\\film-front\\static\\json");
        inputJsonFiles2Redis(file);
    }*/
}
