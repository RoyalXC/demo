package com.example.demo.deal;

import com.example.demo.common.spi.po.entity.DealEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    private static ConcurrentHashMap<Date, List<DealEntity>> dealMap = new ConcurrentHashMap<>();
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor executor;

    private static List<DealEntity> dealList = new CopyOnWriteArrayList<>();

    public static List<DealEntity> getDealList() {
        return dealList;
    }

    public static ConcurrentHashMap<Date, List<DealEntity>> getDealMap() {
        return dealMap;
    }

    public synchronized static List<DealEntity> getPerSecondList() {
        Date time = getMinute();
        LOGGER.info("time:{}", time);
//        List<DealEntity> result = new ArrayList<>();
//        for (DealEntity entity : dealList) {
//            if (entity.getTimestamp().before(time)) {
//                result.add(entity);
//                dealList.remove(entity);
//            }
//        }
        List<DealEntity> result = dealMap.get(time);
        return result == null ? new ArrayList<>() : result;
    }

    private static Date getMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static Date getMinute(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static boolean flag = true;

    public static void setFlag(boolean flag) {
        DealService.flag = flag;
    }

    public void createDealInfo() {
        executor.execute(() -> {
            while (flag) {
                DealEntity dealEntity = new DealEntity();
                Date time = getMinute(dealEntity.getTimestamp());
                List<DealEntity> list = dealMap.computeIfAbsent(time, k -> new ArrayList<>());
                list.add(dealEntity);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
