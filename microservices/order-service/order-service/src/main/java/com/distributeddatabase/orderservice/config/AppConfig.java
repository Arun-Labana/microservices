package com.distributeddatabase.orderservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**") // Allow CORS for all paths
                    .allowedOrigins("*") // Allow requests from all origins
                    .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specified methods
                    .allowedHeaders("*"); // Allow all headers
        }
    }
}

