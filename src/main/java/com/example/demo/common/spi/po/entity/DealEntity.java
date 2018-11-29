package com.example.demo.common.spi.po.entity;

import com.example.demo.common.spi.config.RandomConfig;
import com.example.demo.common.spi.enums.StockInfoEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述:
 *
 * @author 薛行晨(RoyalXC)
 * @date 2018/11/29 11:04
 */
public class DealEntity extends BaseEntity {
    private Long dealId;
    private String stockCode;
    private String stockName;
    private Date timestamp;
    private BigDecimal price;
    private Long amount;

    public DealEntity() {
        this.dealId = RandomConfig.getDealId();
        StockInfoEnum stockInfo = StockInfoEnum.random();
        this.stockCode = stockInfo.getCode();
        this.stockName = stockInfo.getName();
        this.timestamp = new Date();
        this.price = new BigDecimal(Math.abs(RandomConfig.random.nextDouble() * 100));
        this.amount = (long) Math.abs(RandomConfig.random.nextInt() % 10000 + 100);
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
