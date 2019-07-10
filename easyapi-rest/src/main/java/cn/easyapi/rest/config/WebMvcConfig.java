package cn.easyapi.rest.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @Author yangtc
 * @Description MVC视图配置
 * @Date 19:20 2018/12/17
 * @Param
 * @return
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers( registry );
    }*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public SmartLocaleResolver localeResolver() {
        SmartLocaleResolver slr = new SmartLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }


    class SmartLocaleResolver extends AcceptHeaderLocaleResolver {
        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            if (StringUtils.isBlank(request.getHeader("Accept-Language"))) {
                return Locale.getDefault();
            }
            List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
            return Locale.lookup(list, Arrays.asList(Locale.ENGLISH, Locale.CHINESE));
        }
    }
}
