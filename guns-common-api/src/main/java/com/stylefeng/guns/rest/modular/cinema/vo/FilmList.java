package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alex_Cheng
 * @date 2019/7/17 21:35
 * @Description TODO
 */
@Data
public class FilmList  implements  Serializable{
    private static final long serialVersionUID = -7727685632585360718L;
    private Integer filmId;
    private String filmName ;
    private String filmLength ;
    private String filmType ;
    private String filmCats;
    private String actors ;
    private String imgAddress ;
    private List<FilmFields> filmFields ;
}
