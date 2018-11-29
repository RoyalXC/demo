package com.example.demo.deal;

import com.example.demo.common.spi.po.entity.DealEntity;
import com.example.demo.common.spi.po.entity.QuotationEntity;
import com.example.demo.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 功能描述:
 *
 * @author 薛行晨(RoyalXC)
 * @date 2018/11/29 13:35
 */
@Service
public class DealService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealService.class);

    private static boolean flag = true;

    public static void setFlag(boolean flag) {
        DealService.flag = flag;
    }

    private static ConcurrentHashMap<Date, List<DealEntity>> dealMap = new ConcurrentHashMap<>();

    private static List<DealEntity> dealList = new CopyOnWriteArrayList<>();

    private static Map<String, Map<Date, QuotationEntity>> quotationMap = new ConcurrentHashMap<>();

    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor executor;

    public static List<DealEntity> getDealList() {
        return dealList;
    }

    public static Map<String, Map<Date, QuotationEntity>> getQuotationMap() {
        return quotationMap;
    }


    public static ConcurrentHashMap<Date, List<DealEntity>> getDealMap() {
        return dealMap;
    }

    public synchronized static List<DealEntity> getPerSecondList() {
        Date time = DateUtils.getMinute();
        LOGGER.info("time:{}", time);
        List<DealEntity> result = dealMap.get(time);
        return result == null ? new ArrayList<>() : result;
    }

    public void createDealInfo() {
        executor.execute(() -> {
            while (flag) {
                DealEntity dealEntity = new DealEntity();
                Date time = DateUtils.getMinute(dealEntity.getTimestamp());
                List<DealEntity> list = dealMap.computeIfAbsent(time, k -> new ArrayList<>());
                list.add(dealEntity);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createDealList() {
        executor.execute(() -> {
            while (flag) {
                DealEntity dealEntity = new DealEntity();
                LOGGER.info("create a deal :{}", dealEntity.getDealId());
                dealList.add(dealEntity);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createquotation() {
        executor.execute(() -> {
            while (flag) {
                if (dealList.size() > 0) {
                    DealEntity entity = dealList.get(0);
                    LOGGER.info("remove a deal :{}", entity.getDealId());
                    dealList.remove(0);
                    String stockCode = entity.getStockCode();
                    Map<Date, QuotationEntity> quotationEntityMap = quotationMap.get(stockCode);
                    if (quotationEntityMap == null) {
                        quotationEntityMap = new ConcurrentHashMap<>();
                        quotationMap.put(stockCode, quotationEntityMap);
                    }
                    Date date = DateUtils.getMinute(entity.getTimestamp());
                    QuotationEntity quotationEntity = quotationEntityMap.get(date);
                    if (quotationEntity == null) {
                        quotationEntity = new QuotationEntity(entity);
                        quotationEntityMap.put(date, quotationEntity);
                    } else {
                        BigDecimal price = entity.getPrice();
                        if (quotationEntity.getMax().compareTo(price) < 0) {
                            quotationEntity.setMax(price);
                        }
                        if (quotationEntity.getMin().compareTo(price) > 0) {
                            quotationEntity.setMin(price);
                        }
                        quotationEntity.setClose(price);
                        quotationEntity.setNow(price);
                    }
                }
            }
        });
    }
}
