package com.softeem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 * @author 高玉好
 * @ClassName SwaggerConfig
 * @since 2021/1/28 1:31
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.softeem"})
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    //大标题
                    .title("软帝OA项目接口文档")
                    //描述
                    .description("软帝OA项目接口测试")
                    //版本号
                    .version("v1.0.0")
                    .license("").licenseUrl("").termsOfServiceUrl("").build();
    }
}
