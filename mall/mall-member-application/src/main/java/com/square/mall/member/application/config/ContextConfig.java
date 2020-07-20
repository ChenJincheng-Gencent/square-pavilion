package com.square.mall.member.application.config;

import com.square.mall.member.application.filter.AuthInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 描述
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Configuration
@EnableWebMvc
@RestController
@ComponentScan(basePackages = {"com.square.mall.member.application.controller"}, useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(RestController.class))
public class ContextConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ServletRegistrationBean restServlet() {
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("com.square.mall.member.application.controller");
        //通过构造函数指定dispatcherServlet的上下文
        DispatcherServlet restDispatcherServlet = new DispatcherServlet(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(restDispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings("/api/*");
        return registrationBean;
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry tradeCoreRegistryService) {
        InterceptorRegistration registration = tradeCoreRegistryService.addInterceptor(authInterceptor()).addPathPatterns("/**");
        registration.excludePathPatterns("/**/api");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
