package com.example.demo.common.spi.enums;

public enum BaseResultEnum {
    UNKNOWN_ERROR(0, "未知错误"),
    SUCCESS(1, "success"),
    ERROR(-1, "error");

    private Integer code;
    private String msg;

    BaseResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}