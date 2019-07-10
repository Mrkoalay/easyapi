package cn.easyapi.rest.cron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@EnableScheduling
@Component
public class QuotationJob {

    @Autowired
    private RedisTemplate redisTemplate;


    @Scheduled(cron = "*/10 * * * * ?")
    public void execute() {
            //log.info("获取币种" + walletTypeEnum.getDesc() + "行情价：" + bid);
        }
    }
