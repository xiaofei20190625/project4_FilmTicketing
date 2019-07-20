package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex_Cheng
 * @date 2019/7/19 19:49
 * @Description TODO
 */
@Data
public class FilmInfo implements Serializable {
   private String actors;
   private String filmCats;
   private String filmFields;
   private String filmId;
   private String filmLength;
   private String filmName;
   private String filmType;
   private String imgAddress;
}
