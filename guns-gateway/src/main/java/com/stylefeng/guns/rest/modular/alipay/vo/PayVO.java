package com.stylefeng.guns.rest.modular.alipay.vo;


public class PayVO {
    Integer status;
    String imgPre;
    Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImgPre() {
        return imgPre;
    }

    public void setImgPre(String imgPre) {
        this.imgPre = imgPre;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static PayVO ok(Object data){
        PayVO payVO = new PayVO();
        payVO.setData(data);
        String url = "https://sszskkk.oss-cn-beijing.aliyuncs.com/";
        payVO.setImgPre(url);
        payVO.setStatus(0);
        return payVO;
    }

}
