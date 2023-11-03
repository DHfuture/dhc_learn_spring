package com.dhc.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author donghongchen
 * @create 2023/10/24 14:45
 * @Description:
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("dhc2接口文档")
                        .version("1.0.321")
                        .description("dhc这是我么描述信息啦啦啦"));
    }

    /* 分组 */
    @Bean
    public GroupedOpenApi groupedOpenApi1() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedOpenApi2() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedOpenApi3() {
        return GroupedOpenApi.builder()
                .group("rpc")
                .pathsToMatch("/rpc/**")
                .build();
    }

}
