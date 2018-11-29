package com.example.demo.common.spi.config;

import java.util.Random;

/**
 * 功能描述:
 *
 * @author 薛行晨(RoyalXC)
 * @date 2018/11/29 14:32
 */
public class RandomConfig {
    public static Random random = new Random((long) (Math.random() * 100));
    private static volatile Long dealId = 1L;

    public static Long getDealId() {
        RandomConfig.dealId += 1;
        return RandomConfig.dealId;
    }

    private static volatile Long quotationId = 1L;

    public static Long getQuotationId() {
        RandomConfig.quotationId += 1;
        return RandomConfig.quotationId;
    }
}
