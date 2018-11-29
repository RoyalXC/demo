package com.example.demo.quotation.service.impl;

import com.example.demo.common.spi.base.JsonResult;
import com.example.demo.common.spi.po.entity.DealEntity;
import com.example.demo.common.spi.po.entity.QuotationEntity;
import com.example.demo.deal.DealService;
import com.example.demo.quotation.dao.QuotationDao;
import com.example.demo.quotation.service.QuotationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述：
 *
 * @author: 薛行晨(RoyalXC)
 * @date: 2018/11/29 10:30
 */
@Service
public class QuotationServiceImpl implements QuotationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuotationServiceImpl.class);
    @Autowired
    QuotationDao quotationDao;

    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor executor;

    @Override
    public JsonResult test() {
        List<QuotationEntity> list = quotationDao.selectAll();
        return new JsonResult<>(list);
    }

    @Override
    public JsonResult getDealList() {
        return new JsonResult<>(DealService.getDealList());
    }

    @Override
    public JsonResult getDealMap() {
        return new JsonResult<>(DealService.getDealMap());
    }

    @Override
    public JsonResult getPreMinuteDeal() {
        List<DealEntity> list = DealService.getPerSecondList();
        return new JsonResult<>(list);
    }

    @Override
    public JsonResult getPreMinuteQuotation() {
        List<DealEntity> list = DealService.getPerSecondList();
        Map<String, List<DealEntity>> map = list.parallelStream()
                .sorted(Comparator.comparing(DealEntity::getPrice))
                .collect(Collectors.groupingBy(DealEntity::getStockCode));
        return new JsonResult<>(map);
    }

    @Override
    public JsonResult getQuotationMap() {
        return new JsonResult<>(DealService.getQuotationMap());
    }
}
