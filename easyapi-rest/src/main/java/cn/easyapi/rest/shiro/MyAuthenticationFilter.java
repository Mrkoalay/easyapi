package cn.easyapi.rest.shiro;

import cn.easyapi.common.enums.I18nEnum;
import cn.easyapi.common.utils.Response;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author yangtc
 * @Description // 未认证请求过滤器
 * @Date 22:41 2018/12/20
 * @Param
 * }
 * @return
 **/
public class MyAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception{
            PrintWriter out = null;
            HttpServletResponse res = (HttpServletResponse) response;
            try {
                res.setCharacterEncoding("UTF-8");
                res.setContentType("application/json");
                out = response.getWriter();
                Response response1 = Response.error((I18nEnum.UN_LOGIN.getValue()));
                out.println(new JSONObject(response1));
            } catch (Exception e) {
            } finally {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            }
            return false;
    }
}