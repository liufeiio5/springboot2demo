package com.qgwy.alpha_web_manager.enums;

/**
 *异常码
 */
public enum ServiceCode {
    UNKONW_EXCEPTION(500,"未知的异常，请联系管理员"),
    USER_NOT_LOGIN(601,"user is not login"),
    TOKEN_IS_VAILD(602,"token is valid")
    ;
    private int code;
    private String msg;

    ServiceCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
