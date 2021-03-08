package org.charter.democharter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
})
@EnableAsync
@EnableSwagger2
public class DemocharterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemocharterApplication.class, args);
    }

    @Bean
    public Docket getApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.charter.democharter"))
                .paths(PathSelectors.any())
                .build();

    }
}
