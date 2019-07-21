package com.stylefeng.guns.rest.modular.alipay.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.alipay.service.AliPayService;
import com.stylefeng.guns.rest.modular.alipay.vo.OrderStatus;
import com.stylefeng.guns.rest.modular.alipay.vo.PayStatusVO;
import com.stylefeng.guns.rest.modular.alipay.vo.PayVO;
import com.stylefeng.guns.rest.modular.alipay.vo.QRcode;
import com.stylefeng.guns.rest.modular.order.service.IOrderTService;
import com.stylefeng.guns.rest.modular.user.vo.UserResponseVO;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class ALiPayController {
    /*1、	获取支付二维码
    	请求地址
    /order/getPayInfo
    	请求方式
    post
    orderId
    */
    @Reference(interfaceClass = AliPayService.class)
    AliPayService aliPayService;
    @PostMapping("/getPayInfo")
    @ResponseBody
    public Object getPayInfo(Integer orderId) {
        try{
            String QRCodeAddress = aliPayService.getPayInfo(orderId);
            if (QRCodeAddress!=null){
                QRcode qRcode = new QRcode();
                qRcode.setOrderId(orderId);
                qRcode.setQRCodeAddress(QRCodeAddress);
                return PayVO.ok(qRcode);
            }
            return UserResponseVO.fail(1,"订单支付失败，请稍后重试");
        }catch (Exception e){
            return UserResponseVO.fail();
        }

    }

    /*2、	获取支付结果
	请求地址
/order/getPayResult
	请求方式
post
	请求字段
请求字段	字段含义	是否必填
orderId	订单编号	是
tryNums	重试次数	否，默认为1,4次超时

*/
    @PostMapping("/getPayResult")
    @ResponseBody
    public Object getPayResult(Integer orderId,@RequestParam(defaultValue = "1")Integer tryNums) {
        try{
            if (tryNums>3){
                return UserResponseVO.fail(1,"订单支付失败，请稍后重试");
            }
            Boolean aliPayResult = aliPayService.getPayResult(orderId,tryNums);
            if (aliPayResult){
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setOrderId(orderId);
                orderStatus.setOrderMsg("支付成功");
                orderStatus.setOrderStatus(1);
                return PayStatusVO.ok(orderStatus);
            }
            return UserResponseVO.fail(1,"订单支付失败，请稍后重试");

        }catch (Exception e){
            return UserResponseVO.fail();
        }

    }

}
