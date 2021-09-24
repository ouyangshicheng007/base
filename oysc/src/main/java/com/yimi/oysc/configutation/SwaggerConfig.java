package com.yimi.oysc.configutation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * Auto-Generated
 *
 * swagger3访问地址：
 * http://localhost:7788/oysc/swagger-ui/index.html
 *
 * @Author Davy
 * @Date 2021/6/25 11:58
 **/
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean // 配置docket以配置Swagger具体参数
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)//文档类型
                .apiInfo(apiInfo())// 关联配置文档
                .select()//扫描方法
                .apis(RequestHandlerSelectors.basePackage("com.yimi.oysc.controller"))//扫描包路径
                .paths(PathSelectors.any())//路径过滤
                .build();//构建
    }

    // 配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        return new ApiInfo(
                "Swagger学习", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
