package com.stylefeng.guns.rest.modular.product.controller;


import com.stylefeng.guns.rest.common.persistence.model.Product;
import com.stylefeng.guns.rest.modular.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-15
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @ResponseBody
    @RequestMapping("/getProductById")
    public Product getProductById(Integer id){

        Product product = productService.selectById(id);

        return product;
    }


    @ResponseBody
    @RequestMapping("/insertProduct")
    public String insertProduct(@RequestBody Product product){

        boolean insert = productService.insert(product);

        if (insert){

            return "success";

        }else{
            return "fail";

        }
    }

}

