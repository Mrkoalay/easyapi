package cn.easyapi.rest.config;

import cn.easyapi.rest.lock.RedissLockUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yangtc
 * @Description //redisson配置
 * @Date 16:46 2018/12/28
 * @Param
 * @return
 **/
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private  String host;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient getRedisson(){
            RedissonClient redisson = null;
            Config config = new Config();
            SingleServerConfig singleServerConfig = config.useSingleServer().setAddress("redis://"+host+":6379");
            if(StringUtils.isNotBlank(password)){
                singleServerConfig.setPassword(password);
            }
            redisson = Redisson.create(config);
            RedissLockUtil.setLocker(redisson);
            return redisson;

    }

}

