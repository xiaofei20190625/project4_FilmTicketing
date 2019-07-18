package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

public class YearInfo implements Serializable {
    private static final long serialVersionUID = 1383151251093583228L;

    private Boolean active;

    private Integer yearId;

    private String yearName;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    @Override
    public String toString() {
        return "YearInfo{" +
                "active=" + active +
                ", yearId=" + yearId +
                ", yearName='" + yearName + '\'' +
                '}';
    }
}
