package com.zdsy.drivingschoolreservationsystem.constant;

public enum WxCodeType {
    systemBussy(-1, "系统繁忙，此时请开发者稍候再试"),
    success(0, "请求成功"),
    codeInvalid(40029, "code 无效"),
    frequencyLimit(45011, "频率限制，每个用户每分钟100次"),
    hightRiskInterception(40226, "高风险等级用户，小程序登录拦截 。风险等级详见用户安全解方案"),
    ;

    private int code;

    private String message;

    private WxCodeType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
