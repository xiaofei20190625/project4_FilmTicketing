package com.stylefeng.guns.rest.modular.film.model;

import java.io.Serializable;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/15 Time 21:39
 */
public class Banner implements Serializable {
    private Integer bannerId;

    private String bannerAddress;

    private String bannerUrl;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerAddress() {
        return bannerAddress;
    }

    public void setBannerAddress(String bannerAddress) {
        this.bannerAddress = bannerAddress;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    @Override
    public String toString() {
        return "Banners{" +
                "bannerId=" + bannerId +
                ", bannerAddress='" + bannerAddress + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                '}';
    }
}
