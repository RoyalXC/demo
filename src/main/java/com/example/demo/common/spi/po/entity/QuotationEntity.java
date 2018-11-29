package com.example.demo.common.spi.po.entity;

import com.example.demo.common.spi.config.RandomConfig;
import com.example.demo.common.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述:
 *
 * @author 薛行晨(RoyalXC)
 * @date 2018/11/29 11:04
 */
public class QuotationEntity extends BaseEntity {
    private Long quotationId;
    private String stockCode;
    private String stockName;
    private Date timestamp;
    private BigDecimal max;
    private BigDecimal min;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal now;

    public QuotationEntity(DealEntity entity) {
        this.quotationId = RandomConfig.getQuotationId();
        this.stockCode = entity.getStockCode();
        this.stockName = entity.getStockName();
        this.timestamp = DateUtils.getMinute(entity.getTimestamp());
        this.max = entity.getPrice();
        this.min = entity.getPrice();
        this.open = entity.getPrice();
        this.close = entity.getPrice();
        this.now = entity.getPrice();
    }

    public Long getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getNow() {
        return now;
    }

    public void setNow(BigDecimal now) {
        this.now = now;
    }
}
