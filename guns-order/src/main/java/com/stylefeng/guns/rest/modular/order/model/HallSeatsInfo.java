package com.stylefeng.guns.rest.modular.order.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/19 Time 20:48
 */
public class HallSeatsInfo implements Serializable {
    private static final long serialVersionUID = -7395087712436976822L;

    private Integer limit;

    private String ids;

    private List<List<Single>> single;

    private List<List<Couple>> couple;

    public HallSeatsInfo() {
    }

    public HallSeatsInfo(Integer limit, String ids, List<List<Single>> single, List<List<Couple>> couple) {
        this.limit = limit;
        this.ids = ids;
        this.single = single;
        this.couple = couple;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public List<List<Single>> getSingle() {
        return single;
    }

    public void setSingle(List<List<Single>> single) {
        this.single = single;
    }

    public List<List<Couple>> getCouple() {
        return couple;
    }

    public void setCouple(List<List<Couple>> couple) {
        this.couple = couple;
    }

    @Override
    public String toString() {
        return "HallSeatsInfo{" +
                "limit=" + limit +
                ", ids='" + ids + '\'' +
                ", single=" + single +
                ", couple=" + couple +
                '}';
    }
}
