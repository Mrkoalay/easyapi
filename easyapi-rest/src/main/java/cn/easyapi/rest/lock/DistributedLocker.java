package cn.easyapi.rest.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @Description 分布式锁
 * @Author yangtc
 * @Date 2018/12/28 16:47
 */
public interface DistributedLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, long timeout);

    RLock lock(String lockKey, TimeUnit unit, long timeout);

    boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);

    void setRedissonClient(RedissonClient redissonClient);
}
