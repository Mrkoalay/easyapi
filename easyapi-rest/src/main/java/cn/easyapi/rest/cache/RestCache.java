package cn.easyapi.rest.cache;

import cn.easyapi.common.entity.User;
import cn.easyapi.rest.cache.base.CacheKey;
import cn.easyapi.rest.cache.base.CacheKit;
import cn.easyapi.rest.cache.loder.UserLoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 所有缓存
 * @Author yangtc
 * @Date 2018/8/27 下午3:49
 */
@Component
public class RestCache implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RestCache.class);

    @Autowired
    private UserLoder userLoder;



    @Override
    public void run(String... args) {
        try {
            log.info("=====>初始化缓存开始");

            // 用户缓存
            List<User> users = (List<User>) userLoder.load();
            CacheKit.put(CacheKey.USER, CacheKey.USER, users);

            log.info("=====>初始化缓存结束");
        } catch (Exception e) {
            log.error("!!!初始化缓存异常", e);
        }
    }
}
