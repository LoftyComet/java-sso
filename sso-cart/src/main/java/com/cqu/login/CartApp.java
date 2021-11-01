package com.cqu.login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CartApp {
    public static void main(String[] args) {
        SpringApplication.run(CartApp.class, args);
    }
    // 将RestTemplste放入springboot容器
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
