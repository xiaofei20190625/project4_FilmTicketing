package com.stylefeng.guns.rest.modular.cinema.util;

import com.stylefeng.guns.rest.modular.cinema.vo.AreaVo;
import com.stylefeng.guns.rest.modular.cinema.vo.BrandVo;
import com.stylefeng.guns.rest.modular.cinema.vo.HallTypeVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CinemaConditionResponseVo implements Serializable {
    private static final long serialVersionUID = 7695729669331391021L;
    List<BrandVo> brandList;
    List<AreaVo> areaList;
    List<HallTypeVo> halltypeList;
}
