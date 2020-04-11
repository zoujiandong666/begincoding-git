package study.mengduo.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @aothor mengDuo
 * @date 2020/4/5 16:15
 */

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(apiInfo())

                .select()

                .apis(RequestHandlerSelectors.basePackage("study.mengduo.controller"))//单路径扫描

                .paths(PathSelectors.any())

                .build();

    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()

                .title("蒙多校园项目接口文档")//项目描述1

                .description("自学项目，提高编码水平")//项目描述2

                .version("1.0")

                .build();

    }

}
