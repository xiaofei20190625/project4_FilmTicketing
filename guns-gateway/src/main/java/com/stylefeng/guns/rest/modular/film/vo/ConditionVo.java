package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/19 Time 15:34
 */
public class ConditionVo implements Serializable {
    private static final long serialVersionUID = -5843656500276782385L;
    
    private List<CatInfo> catInfo;

    private List<SourceInfo> sourceInfo;

    private List<YearInfo> yearInfo;

    public ConditionVo() {
    }

    public ConditionVo(List<CatInfo> catInfo, List<SourceInfo> sourceInfo, List<YearInfo> yearInfo) {
        this.catInfo = catInfo;
        this.sourceInfo = sourceInfo;
        this.yearInfo = yearInfo;
    }

    public List<CatInfo> getCatInfo() {
        return catInfo;
    }

    public void setCatInfo(List<CatInfo> catInfo) {
        this.catInfo = catInfo;
    }

    public List<SourceInfo> getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(List<SourceInfo> sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public List<YearInfo> getYearInfo() {
        return yearInfo;
    }

    public void setYearInfo(List<YearInfo> yearInfo) {
        this.yearInfo = yearInfo;
    }

    @Override
    public String toString() {
        return "ConditionVo{" +
                "catInfo=" + catInfo +
                ", sourceInfo=" + sourceInfo +
                ", yearInfo=" + yearInfo +
                '}';
    }
}
