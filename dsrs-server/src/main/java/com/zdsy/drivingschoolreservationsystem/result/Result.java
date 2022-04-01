package com.zdsy.drivingschoolreservationsystem.result;

import java.io.Serializable;

import com.zdsy.drivingschoolreservationsystem.constant.ResultCode;

import lombok.Data;

@Data
public class Result implements Serializable {
    private Object response;
    private Integer code;
    private String message;

    private Result(Integer code) {
        this.code = code;
    }

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    public static Result success() {
        return new Result(ResultCode.success);
    }

    public static Result success(Object response) {
        Result result = new Result(ResultCode.success);
        result.response = response;
        return result;
    }

    public static Result failed() {
        return new Result(ResultCode.failed);
    }

    public static Result failed(ResultCode resultCode) {
        return new Result(resultCode.getCode(), resultCode.getMessage());
    }

    public static Result failed(String message) {
        return new Result(ResultCode.failed.getCode(), message);
    }
}
