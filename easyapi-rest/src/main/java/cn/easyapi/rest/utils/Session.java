package cn.easyapi.rest.utils;

import cn.easyapi.common.entity.User;
import cn.easyapi.rest.constants.SessionFieldConstants;
import cn.easyapi.rest.utils.constants.Constants;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Session {
    private static Logger logger = LoggerFactory.getLogger(Session.class);
    private static ThreadLocal<Boolean> TEMP_ADMIN = ThreadLocal.withInitial(() -> Boolean.FALSE);

    public static Boolean getAdmin() {
        return TEMP_ADMIN.get();
    }

    public static void setAdmin() {
        TEMP_ADMIN.set(Boolean.TRUE);
    }

    public static void clearAdmin() {
        TEMP_ADMIN.remove();
    }

    public static JSONObject getAminUserFromSession() {
        return (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
    }

    public static User getUserFromSession() {
        try {
            return (User) SecurityUtils.getSubject().getSession().getAttribute(SessionFieldConstants.USER);
        } catch (Exception e) {
            logger.error("!!!!!获取用户信息异常", e.getMessage());
        }
        return null;

    }

    public static Object getAttribute(Object o) {
        return SecurityUtils.getSubject().getSession().getAttribute(o);
    }

    public static void setAttribute(Object o, Object o1) {
        SecurityUtils.getSubject().getSession().setAttribute(o, o1);
    }

    public static void setUser(User user) {
        setAttribute(SessionFieldConstants.USER, user);
    }

}
