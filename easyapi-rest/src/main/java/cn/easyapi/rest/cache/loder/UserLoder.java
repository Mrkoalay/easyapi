package cn.easyapi.rest.cache.loder;

import cn.easyapi.rest.cache.base.ILoader;
import cn.easyapi.rest.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 用户缓存加载器
 * @Author yangtc
 * @Date 2018/9/14 下午2:38
 */
@Component
public class UserLoder implements ILoader {
    @Autowired
    private UserService userService;

    @Override
    public Object load() {
        return userService.list(new QueryWrapper<>());
    }
}
