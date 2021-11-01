package com.cqu.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MainAPP {
    public static void main(String[] args) {
        SpringApplication.run(MainAPP.class, args);
    }
    // 将RestTemplste放入springboot容器
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
