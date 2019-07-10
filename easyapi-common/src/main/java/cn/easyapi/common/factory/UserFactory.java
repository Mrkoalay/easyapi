package cn.easyapi.common.factory;

import cn.easyapi.common.entity.User;
import cn.easyapi.common.utils.ToolUtil;

/**
 * @Description 用户创建工厂
 * @Author yangtc
 * @Date 2018/12/23 11:59
 */
public class UserFactory {
    public static User buildUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(ToolUtil.encryptPassword(password));
        return user;
    }

}
