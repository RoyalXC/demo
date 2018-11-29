package com.example.demo.common.spi.enums;

import com.example.demo.common.spi.config.RandomConfig;

public enum StockInfoEnum {
    THS("300033", "同花顺"),
    GZMT("600519", "贵州茅台"),
    NYYH("601288", "农业银行"),
    CHKJ("002288", "超华科技"),
    XKHB("002015", "霞客环保");

    private String code;
    private String name;

    StockInfoEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static StockInfoEnum random() {
        return values()[Math.abs(RandomConfig.random.nextInt() % 5)];
    }
}