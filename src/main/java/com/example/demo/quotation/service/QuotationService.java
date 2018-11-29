package com.example.demo.quotation.service;

import com.example.demo.common.spi.base.JsonResult;

/**
 * 功能描述：
 *
 * @author: 薛行晨(RoyalXC)
 * @date: 2018/11/29 10:30
 */
public interface QuotationService {
    JsonResult test();

    JsonResult getDealList();

    JsonResult getPreMinuteDeal();

    JsonResult getDealMap();

    JsonResult getPreMinuteQuotation();
}
