package cn.easyapi.rest.service;


import cn.easyapi.common.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @since 2017-10-28
 */
public interface UserService extends IService<User> {
    /**
     * @Author yangtc
     * @Description // 校验用户
     * @Date 22:45 2018/12/20
     * @Param
     * @return
     **/
    User checkLogin(String username, String password);

}
