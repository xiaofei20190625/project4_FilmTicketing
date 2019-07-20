package com.stylefeng.guns.cinema;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.modular.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaQueryVo;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Alex_Cheng
 * @date 2019/7/17 21:01
 * @Description TODO
 */
public class MyTest {
/*    @Autowired
    MtimeCinemaTMapper cinemaTMapper;
    @Autowired
    CinemaServiceAPI serviceAPI;
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

    @Test
    public  void  test3(){
        CinemaQueryVo cinemaQueryVo = new CinemaQueryVo(1, 99, 99, 12, 1);
        Page<CinemaVo> cinemas = serviceAPI.getCinemas(cinemaQueryVo);
        List<CinemaVo> records = cinemas.getRecords();
        for (int i = 0; i <records.size() ; i++) {
            System.out.println("records = " + records.get(i));
        }
    }*/
}
