package com.example.demo;


import java.io.Serializable;

public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = 4237689796154905896L;
    private static final Integer SUCCESS = BaseResultEnum.SUCCESS.getCode();
    private static final Integer ERROR = BaseResultEnum.ERROR.getCode();
    private static final String SUCCESS_DEFAULT_CODE = BaseResultEnum.SUCCESS.getMsg();
    private static final String ERROR_DEFAULT_CODE = BaseResultEnum.ERROR.getMsg();

    private Integer error;
    private String message;
    private String innerCode;
    private String innerMsg;
    T data;

    public JsonResult() {
        this.error = SUCCESS;
        this.message = SUCCESS_DEFAULT_CODE;
        this.data = null;
    }

    public JsonResult(T data) {
        this.error = SUCCESS;
        this.message = SUCCESS_DEFAULT_CODE;
        this.data = data;
    }

    public JsonResult(Integer error, String message, T data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public JsonResult(CommonCode commonCode) {
        this.error = ERROR;
        this.message = ERROR_DEFAULT_CODE;
        this.innerMsg = commonCode.getSubMsg();
        this.innerCode = commonCode.getSubCode();
        this.data = null;
    }

    public JsonResult(CommonCode commonCode, String message) {
        this.error = ERROR;
        this.message = message;
        this.innerCode = commonCode.getSubCode();
        this.innerMsg = commonCode.getSubMsg();
        this.data = null;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getInnerMsg() {
        return innerMsg;
    }

    public void setInnerMsg(String innerMsg) {
        this.innerMsg = innerMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
