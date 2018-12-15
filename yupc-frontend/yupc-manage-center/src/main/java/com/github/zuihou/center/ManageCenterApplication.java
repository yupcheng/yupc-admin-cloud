package com.github.yupc.center;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({
        "com.github.yupc.admin.rest.account.api",
        "com.github.yupc.admin.rest.authority.api",
        "com.github.yupc.auth.api",
})
@EnableHystrix
public class ManageCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCenterApplication.class, args);
    }
}
