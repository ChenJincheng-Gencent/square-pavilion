
package com.square.mall.member.application.config;

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
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

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

    @Bean(value = "memberApplication")
    @Order(value = 2)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.square.mall.member.application"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(CollectionUtils.newArrayList())
                .securitySchemes(CollectionUtils.newArrayList());
    }

    private ApiInfo groupApiInfo(){
        return new ApiInfoBuilder()
                .title("四方阁会员应用")
                .description("本项目用于提供会员相关业务的应用层功能")
                .termsOfServiceUrl("192.168.31.10:9200")
                .contact(new Contact("Gencent", "https://github.com/ChenJincheng-Gencent",
                    "402634287@qq.com"))
                .version("1.0")
                .build();
    }

}
