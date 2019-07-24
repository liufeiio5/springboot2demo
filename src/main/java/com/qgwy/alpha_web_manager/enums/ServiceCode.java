package com.qgwy.alpha_web_manager.enums;

/**
 *异常码
 */
public enum ServiceCode {
    UNKONW_EXCEPTION("500","未知的异常，请联系管理员"),
    USER_NOT_LOGIN("601","用户未登录"),
    TOKEN_IS_VAILD("602","token无效"),
    NAME_OR_PASS_ERROR("603","用户名或密码错误")
    ;
    private String code;
    private String msg;

    ServiceCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

