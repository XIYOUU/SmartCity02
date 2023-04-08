package com.example.smartcity02.commonBean;

public class LoginBean {

    /**
     * msg : 操作成功
     * code : 200
     * token : eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImQ1ZTVmMDgzLWFiODMtNDViMy1hZDIzLTlmNTk1Yjk4NWUyNCJ9.MwJ1JKGbjzljBgM79Y-z-dU8AD5CvBD7yMUCghatiK0K7qTP3VfDsKsu6ZC1eR5zrZQA6eXXwQ0aR6FglykshQ
     */

    private String msg;
    private int code;
    private String token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
