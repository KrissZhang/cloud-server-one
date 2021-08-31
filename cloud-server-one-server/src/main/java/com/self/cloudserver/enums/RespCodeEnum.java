package com.self.cloudserver.enums;

/**
 * 响应码
 */
public enum RespCodeEnum {
    /**
     * 成功
     */
    SUCCESS("0000", "成功"),

    /**
     * 失败
     */
    FAIL("1001", "失败");

    private String code;

    private String msg;

    RespCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
