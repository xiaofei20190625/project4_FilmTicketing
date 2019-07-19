package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

public class CatInfo implements Serializable {
    private static final long serialVersionUID = 5439880444913623339L;

    private Boolean active;

    private Integer catId;

    private String catName;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return "CatInfo{" +
                "active=" + active +
                ", catId=" + catId +
                ", catName='" + catName + '\'' +
                '}';
    }
}
