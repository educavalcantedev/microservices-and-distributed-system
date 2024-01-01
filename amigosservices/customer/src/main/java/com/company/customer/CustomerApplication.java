package com.company.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.company.customer",
                "com.company.amqp"
        }
)
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.company.clients")
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class);
    }
}
