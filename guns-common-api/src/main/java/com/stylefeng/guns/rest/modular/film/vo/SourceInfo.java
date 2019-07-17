package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

public class SourceInfo implements Serializable {
    private Boolean active;

    private Integer sourceId;

    private String sourceName;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String toString() {
        return "SourceInfo{" +
                "active=" + active +
                ", sourceId=" + sourceId +
                ", sourceName='" + sourceName + '\'' +
                '}';
    }
}
