package com.stylefeng.guns.cinema;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Alex_Cheng
 * @date 2019/7/17 21:01
 * @Description TODO
 */
public class MyTest {
    @Autowired
    MtimeCinemaTMapper cinemaTMapper;
    @Test
    public  void test1(){
        String s= "asdf";
        try{
            int ss = Integer.parseInt(s);
        }catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
        }

    }

    @Test
    public void  test2(){
        List<MtimeCinemaT> cinemaTList = cinemaTMapper.selectList(new EntityWrapper<>());
        for (MtimeCinemaT c:cinemaTList
             ) {
            System.out.println("c = " + c);
        }
    }
}
