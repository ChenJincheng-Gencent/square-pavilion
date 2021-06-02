
package com.square.mall.trade.application.config;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.List;

/**
 * Swagger配置
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
@Profile({"local", "dev", "test","stage"})
public class SwaggerConfig {

    @Bean(value = "tradeApplication")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.square.mall.trade.application"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(CollectionUtils.newArrayList(tokenSecurityContext(), ipSecurityContext(), userIdSecurityContext()))
                .securitySchemes(CollectionUtils.<SecurityScheme>newArrayList(token(), ip(), userId()));
    }

    private ApiInfo groupApiInfo(){
        return new ApiInfoBuilder()
                .title("Square Pavilion Mall Trade Application")
                .description("本项目用于提供交易相关业务的应用层功能")
                .termsOfServiceUrl("localhost:6100")
                .contact(new Contact("Gencent", "https://github.com/ChenJincheng-Gencent",
                    "402634287@qq.com"))
                .version("1.0")
                .build();
    }



    private ApiKey token() {
        return new ApiKey("Token", "Token", "header");
    }
    private ApiKey ip() {
        return new ApiKey("Ip", "Ip", "header");
    }
    private ApiKey userId() {
        return new ApiKey("UserId", "UserId", "header");
    }

    private SecurityContext tokenSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(tokenAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }
    private SecurityContext ipSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(ipAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private SecurityContext userIdSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(userIdAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    List<SecurityReference> tokenAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return CollectionUtils.newArrayList(new SecurityReference("Token", authorizationScopes));
    }
    List<SecurityReference> ipAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return CollectionUtils.newArrayList(new SecurityReference("Ip", authorizationScopes));
    }
    List<SecurityReference> userIdAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return CollectionUtils.newArrayList(new SecurityReference("UserId", authorizationScopes));
    }

}
