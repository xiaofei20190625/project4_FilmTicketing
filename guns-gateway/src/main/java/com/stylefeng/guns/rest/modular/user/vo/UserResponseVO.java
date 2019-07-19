package com.stylefeng.guns.rest.modular.user.vo;

public class UserResponseVO {
    Integer status;
    String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static UserResponseVO ok(){
        UserResponseVO userResponseVO = new UserResponseVO();
        userResponseVO.setStatus(0);
        userResponseVO.setMsg("注册成功");
        return userResponseVO;
    }

    public static UserResponseVO exist() {
        UserResponseVO userResponseVO = new UserResponseVO();
        userResponseVO.setStatus(1);
        userResponseVO.setMsg("用户已存在");
        return userResponseVO;
    }
    public static UserResponseVO fail() {
        UserResponseVO userResponseVO = new UserResponseVO();
        userResponseVO.setStatus(999);
        userResponseVO.setMsg("系统出现异常，请联系管理员");
        return userResponseVO;
    }

    public static UserResponseVO verify() {
        UserResponseVO userResponseVO = new UserResponseVO();
        userResponseVO.setStatus(0);
        userResponseVO.setMsg("验证成功");
        return userResponseVO;
    }

    public static UserResponseVO fail(Integer status,String msg) {
        UserResponseVO userResponseVO = new UserResponseVO();
        userResponseVO.setStatus(status);
        userResponseVO.setMsg(msg);
        return userResponseVO;
    }

    public static UserResponseVO ok(Integer status,String msg) {
        UserResponseVO userResponseVO = new UserResponseVO();
        userResponseVO.setStatus(status);
        userResponseVO.setMsg(msg);
        return userResponseVO;
    }


}
