package com.stylefeng.guns.rest.modular.order.model;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/19 Time 23:39
 */
public class Single {
    private Integer column;

    private Integer row;

    private Integer seatId;

    public Single() {
    }

    public Single(Integer column, Integer row, Integer seatId) {
        this.column = column;
        this.row = row;
        this.seatId = seatId;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "CoupleSeat{" +
                "column=" + column +
                ", row=" + row +
                ", seatId=" + seatId +
                '}';
    }
}
