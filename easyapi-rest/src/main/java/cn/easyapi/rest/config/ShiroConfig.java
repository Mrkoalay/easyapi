package cn.easyapi.rest.config;

import cn.easyapi.rest.shiro.CssSessionManager;
import cn.easyapi.rest.shiro.MyAuthenticationFilter;
import cn.easyapi.rest.shiro.MyShiroRealm;
import cn.easyapi.rest.shiro.ShiroRedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author yangtc
 * @Description //shiro 配置
 * @Date 22:41 2018/12/20
 * @Param
 * @return
 **/
@Configuration
public class ShiroConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    //将自己的验证方式加入容器
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SessionDAO sessionDAO(){
        return new MemorySessionDAO();
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        securityManager.setRealm(myShiroRealm());
        return securityManager;

    }
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();//获取filters
        filters.put("authc", new MyAuthenticationFilter());

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/ajaxLogin", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/login/auth", "anon");
        filterChainDefinitionMap.put("/user/test", "anon");


        // 过滤druid
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 过滤文件预览
        filterChainDefinitionMap.put("/file/preview/**", "anon");

        //调试过滤接口
        //filterChainDefinitionMap.put("/event/**", "anon");
        // 过滤swagger
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }



    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }




    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        CssSessionManager fireSessionManager = new CssSessionManager();
        //这里可以不设置。Shiro有默认的session管理。如果缓存为Redis则需改用Redis的管理
        fireSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return fireSessionManager;
    }

    private ShiroRedisCacheManager cacheManager(){
        return new ShiroRedisCacheManager(redisTemplate);
    }

}