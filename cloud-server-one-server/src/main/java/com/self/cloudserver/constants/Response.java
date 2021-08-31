package com.self.cloudserver.constants;

import java.io.Serializable;

public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String tip;

    private String msg;

    private transient T data;

    public Response() {
        super();
    }

    public Response(String code, String tip, String msg, T data) {
        this.code = code;
        this.tip = tip;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Response <T> ok(){
        Response<T> res = new Response<>();
        res.setCode("0000");
        res.setTip("成功");
        res.setMsg("成功");

        return res;
    }

    public static <T> Response<T> ok(T data){
        Response<T> res = new Response<>();
        res.setCode("0000");
        res.setTip("成功");
        res.setMsg("成功");
        res.setData(data);

        return res;
    }

    public static <T> Response <T> addError(String code, String tip){
        return new Response<>(code, tip, tip, null);
    }

}
