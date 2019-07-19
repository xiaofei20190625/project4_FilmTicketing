package com.stylefeng.guns.rest.modular.auth.vo;


public class AuthResponse {
    int status;
    Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static AuthResponse ok(Object data){
        AuthResponse authResponse = new AuthResponse();
        authResponse.setData(data);
        authResponse.setStatus(0);
        return authResponse;
    }

}
