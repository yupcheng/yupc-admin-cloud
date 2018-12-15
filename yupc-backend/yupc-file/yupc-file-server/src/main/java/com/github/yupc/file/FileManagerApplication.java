package com.github.yupc.file;

import com.github.yupc.auth.client.EnableYupcAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yupc
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableTransactionManagement
@EnableYupcAuthClient
public class FileManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileManagerApplication.class, args);
    }
}
