package com.square.mall.trade.application.config;

import com.square.mall.trade.application.filter.AuthInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 上下文配置
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Configuration
@EnableWebMvc
@RestController
@ComponentScan(basePackages = {"com.square.mall.trade.application.controller"}, useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(RestController.class))
public class ContextConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    @Bean
    public ServletRegistrationBean restServlet() {
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("com.square.mall.trade.application.controller");
        //通过构造函数指定dispatcherServlet的上下文
        DispatcherServlet restDispatcherServlet = new DispatcherServlet(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(restDispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        //registrationBean.addUrlMappings("/api/*");
        return registrationBean;
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        InterceptorRegistration registration = interceptorRegistry.addInterceptor(authInterceptor()).addPathPatterns("/**");
        //swagger相关的URL
        registration.excludePathPatterns("/doc.html");
        registration.excludePathPatterns("/swagger-resources/configuration/ui");
        registration.excludePathPatterns("/swagger-resources");
        registration.excludePathPatterns("/webjars/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*
         * SpringBoot自动配置本身并不会把/swagger-ui.html
         * 这个路径映射到对应的目录META-INF/resources/下面
         * 采用WebMvcConfigurerAdapter将swagger的静态文件进行发布;
         */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
    }

}
