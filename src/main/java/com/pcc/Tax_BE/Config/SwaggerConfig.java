package com.pcc.Tax_BE.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI testApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Tax API")
                        .version("1.0")
                        .description("API test for Tax eiei")
                );
    }
}
