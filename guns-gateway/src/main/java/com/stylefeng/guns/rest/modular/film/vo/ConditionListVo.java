package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/17 Time 17:13
 */
public class ConditionListVo implements Serializable {
    private static final long serialVersionUID = 5205236808844659686L;

    private List<CatInfo> catInfo;

    private List<SourceInfo> sourceInfo;

    private List<YearInfo> yearInfo;

    private String imgPre;

    private String msg;

    private int status;

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

    public String getImgPre() {
        return imgPre;
    }

    public void setImgPre(String imgPre) {
        this.imgPre = imgPre;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConditionListVo{" +
                "catInfo=" + catInfo +
                ", sourceInfo=" + sourceInfo +
                ", yearInfo=" + yearInfo +
                ", imgPre='" + imgPre + '\'' +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
