package study.mengduo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 * @aothor mengDuo
 * @date 2020/4/1 21:25
 */
@Slf4j
@Component
public class MySchedule {

    @Scheduled(fixedRate = 5 * 1000)
    public void fixedRateDemo() {
        log.info("{}","定时任务 Demo ----------");
    }
}
