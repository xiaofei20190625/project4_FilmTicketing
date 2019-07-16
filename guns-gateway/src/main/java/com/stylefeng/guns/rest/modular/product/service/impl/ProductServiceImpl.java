package com.stylefeng.guns.rest.modular.product.service.impl;

import com.stylefeng.guns.rest.common.persistence.model.Product;
import com.stylefeng.guns.rest.common.persistence.dao.ProductMapper;
import com.stylefeng.guns.rest.modular.product.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-15
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
