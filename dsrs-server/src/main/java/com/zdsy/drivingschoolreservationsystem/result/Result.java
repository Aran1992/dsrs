package com.zdsy.drivingschoolreservationsystem.result;

import java.io.Serializable;

import com.zdsy.drivingschoolreservationsystem.constant.ResultCode;

import lombok.Data;

@Data
public class Result implements Serializable {
    private Object response;
    private Integer code;
    private String message;

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result success(Object response) {
        Result result = new Result(ResultCode.success);
        result.response = response;
        return result;
    }

    public static Result success() {
        return new Result(ResultCode.success);
    }
}
