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
import java.util.concurrent.ConcurrentLinkedQueue;
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

    private static boolean quotationFlag = true;

    public static void setFlag(boolean flag) {
        DealService.flag = flag;
    }

    private static ConcurrentHashMap<Date, List<DealEntity>> dealMap = new ConcurrentHashMap<>();

    //    private static List<DealEntity> dealList = Collections.synchronizedList(new ArrayList<>());
    private static ConcurrentLinkedQueue<DealEntity> dealList = new ConcurrentLinkedQueue<>();

    private static Map<String, Map<Date, QuotationEntity>> quotationMap = new ConcurrentHashMap<>();

    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor executor;

    public static ConcurrentLinkedQueue<DealEntity> getDealList() {
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

    public synchronized void createDealInfo() {
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

    public synchronized void createDealList() {
        executor.execute(() -> {
            while (true) {
                long t1 = System.currentTimeMillis();
                List<DealEntity> list = new ArrayList<>();
                while (flag) {
                    DealEntity dealEntity = new DealEntity();
                    LOGGER.info("create a deal :{}", dealEntity.getDealId());
                    list.add(dealEntity);
                    flag = System.currentTimeMillis() - t1 < 1000;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dealList.addAll(list);
                LOGGER.info("add a dealList, list.size:{}, dealList.size:{}", list.size(), dealList.size());
                list.clear();
                flag = true;
            }
        });
    }

    public synchronized void createquotation() {
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            executor.execute(() -> {
                while (true) {
                    DealEntity entity = dealList.poll();
                    if (entity != null) {
                        LOGGER.info("exec num:{},remove a deal :{}", finalI, entity.getDealId());
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
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
