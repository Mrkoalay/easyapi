package cn.easyapi.rest.cache;

import cn.easyapi.common.entity.User;
import cn.easyapi.rest.cache.base.CacheKey;
import cn.easyapi.rest.cache.base.CacheKit;
import cn.easyapi.rest.cache.loder.UserLoder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 用户缓存
 * @Author yangtc
 * @Date 2018/9/4 下午2:35
 */
@Component
public class UserCache implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(UserCache.class);
    @Autowired
    private UserLoder userLoder;

    public List<User> getAll() {
        return CacheKit.get(CacheKey.USER, CacheKey.USER);
    }

    public User getById(Integer id) {
        if (id == null) {
            return null;
        }
        User user = CacheKit.get(CacheKey.USER_ID, id);
        if (user == null) {
            List<User> users = CacheKit.get(CacheKey.USER, CacheKey.USER, userLoder);

            List<User> filters = users.stream().filter(x -> {
                return id.equals(x.getId());
            }).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(filters)) {
                user = filters.get(0);
                CacheKit.put(CacheKey.USER_ID, id, user);
            }
        }
        return user;
    }
    public void refreshCashe() {
        CacheKit.removeAll(CacheKey.USER);
        CacheKit.put(CacheKey.USER, CacheKey.USER, userLoder.load());
    }

}

