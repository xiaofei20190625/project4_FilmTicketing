package com.stylefeng.guns.rest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.modular.film.model.Banner;
import com.stylefeng.guns.rest.modular.film.model.MtimeBannerT;
import com.stylefeng.guns.rest.modular.film.service.IMtimeBannerTService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GunsRestApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	IMtimeBannerTService bannerTService;
	@Test
	public void getIndexBanners() {
		List<MtimeBannerT> mtimeBannerTS = bannerTService.selectList(new EntityWrapper<MtimeBannerT>()
				.setSqlSelect("uuid","bannerAddress","bannerUrl")
				.eq("is_valid", 0)
				.last("limit 5"));
		//新建一个首页用的bannerList
		List<Banner> banners = new ArrayList<>();
		//遍历dao层查到的list，封装到bannerList中去
		for (MtimeBannerT b : mtimeBannerTS){
			Banner banner = new Banner();
			banner.setBannerId(b.getUuid());
			banner.setBannerAddress(b.getBannerAddress());
			banner.setBannerUrl(b.getBannerUrl());
			banners.add(banner);
		}
		for (Banner b : banners){
			System.out.println(b);
		}
	}
}
