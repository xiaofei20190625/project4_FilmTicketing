package com.stylefeng.guns.rest.modular.film.model;

import lombok.Data;

import java.io.Serializable;

//搜索影片的条件
@Data
public class GetFilmCondition implements Serializable {

    private Integer showType;

    private Integer sortId;

    private Integer catId;

    private Integer sourceId;

    private Integer yearId;

    private Integer nowPage;

    private Integer pageSize;


}
