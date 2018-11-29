package com.example.demo.quotation.controller;

import com.example.demo.common.spi.base.JsonResult;
import com.example.demo.quotation.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 *
 * @author 薛行晨(RoyalXC)
 * @date 2018/11/21 0:19
 */
@RestController
@RequestMapping(value = "/quotation")
public class QuotationController {
    @Autowired
    QuotationService quotationService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public JsonResult test() {
        return quotationService.test();
    }

    @RequestMapping(value = "/getDealList", method = RequestMethod.GET)
    public JsonResult getDealList() {
        return quotationService.getDealList();
    }

    @RequestMapping(value = "/getPreMinuteDeal", method = RequestMethod.GET)
    public JsonResult getPreMinuteDeal() {
        return quotationService.getPreMinuteDeal();
    }

    @RequestMapping(value = "/getPreMinuteQuotation", method = RequestMethod.GET)
    public JsonResult getPreMinuteQuotation() {
        return quotationService.getPreMinuteQuotation();
    }

    @RequestMapping(value = "/getDealMap", method = RequestMethod.GET)
    public JsonResult getDealMap() {
        return quotationService.getDealMap();
    }
}
