package com.stylefeng.guns.jwt;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.SimpleObject;
import com.stylefeng.guns.rest.common.persistence.model.Product;
import com.stylefeng.guns.rest.modular.auth.converter.BaseTransferEntity;
import com.stylefeng.guns.rest.modular.auth.security.impl.Base64SecurityAction;

import java.math.BigDecimal;

/**
 * jwt测试
 *
 * @author fengshuonan
 * @date 2017-08-21 16:34
 */
public class DecryptTest {

    public static void main(String[] args) {

        String salt = "pe9zhy";

        /*SimpleObject simpleObject = new SimpleObject();
        simpleObject.setUser("stylefeng");
        simpleObject.setAge(12);
        simpleObject.setName("ffff");
        simpleObject.setTips("code");*/

        Product product=new Product();
        product.setPid(2);
        product.setPname("book");
        product.setPcount(2);
        product.setPrice(new BigDecimal(100));
        product.setDesc("cskaoyan os");

        String jsonString = JSON.toJSONString(product);

        String encode = new Base64SecurityAction().doAction(jsonString);
        String md5 = MD5Util.encrypt(encode + salt);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}
