package cn.easyapi.rest.shiro;

import cn.easyapi.common.entity.User;
import cn.easyapi.rest.constants.SessionFieldConstants;
import cn.easyapi.rest.service.UserService;
import cn.easyapi.rest.utils.constants.Constants;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Author yangtc
 * @Description 自定义权限匹配和账号密码匹配
 * @Date 下午6:32 2018/8/22
 * @Param
 * @return
 **/
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        logger.info("permission的值为:" + permission);
        logger.info("本用户权限为:" + permission.get("permissionList"));
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号.
        String account = (String) token.getPrincipal();
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.checkLogin(account, StringUtils.EMPTY_STRING);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                getName()
        );
        SecurityUtils.getSubject().getSession().setAttribute(SessionFieldConstants.USER, user);
        return authenticationInfo;

    }

}
