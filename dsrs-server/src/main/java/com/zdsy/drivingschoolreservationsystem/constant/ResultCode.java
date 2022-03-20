package com.zdsy.drivingschoolreservationsystem.constant;

public enum ResultCode {
    failed(-1, "请求失败"),
    success(0, "请求成功"),
    noSuchUser(1, "用户不存在"),
    ;

    private int code;

    private String message;

    private ResultCode(int code, String message) {
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
