package com.example.fjdemo.response;

import lombok.Data;

import java.util.List;

/**
 * @author yyh
 */
@Data
public class Result<T> {

    private Integer code = SUCCESS_CODE;
    private String message = SUCCESS_MESSAGE;
    private T data;
    private List<String> errors;
    private Boolean serviceSuccess;
    private String requestId;
    public static final Integer FAIL_CODE = 500;
    public static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MESSAGE = "success";
    private static final String FAIL_MESSAGES = "fail";

    public Result() {
    }

    public Result(Integer code, String message, T data, List<String> errors, Boolean serviceSuccess, String requestId) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.serviceSuccess = serviceSuccess;
        this.requestId = requestId;
    }

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, SUCCESS_MESSAGE, null, null, null, null);
    }

    public static <T> Result<T> success(String requestId) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MESSAGE, null, null, null, requestId);
    }

    public static <T> Result<T> fail() {
        return new Result<>(FAIL_CODE, FAIL_MESSAGES, null, null, null, null);
    }

    public static <T> Result<T> fail(String message, List<String> errors, Boolean serviceSuccess, String requestId) {
        return new Result<>(FAIL_CODE, message, null, errors, serviceSuccess, requestId);
    }

    public static Result<?> fail(String message) {
        return new Result<>(FAIL_CODE, message, null, null, null, null);
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.code);
    }

    public boolean isFail() {
        return !isSuccess();
    }

}



