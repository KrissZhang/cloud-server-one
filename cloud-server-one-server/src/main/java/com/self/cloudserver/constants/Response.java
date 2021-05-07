package com.self.cloudserver.constants;

public class Response<T> {

    private String code;

    private String tip;

    private String msg;

    private T data;

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

    public static Response ok(){
        Response res = new Response();
        res.setCode("0000");
        return res;
    }

    public static Response ok(Object obj){
        Response res = new Response();
        res.setCode("0000");
        res.setData(obj);
        return res;
    }

}
