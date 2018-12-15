package com.github.yupc.auth.client.configuration;

import com.github.yupc.auth.client.config.AppAuthConfig;
import com.github.yupc.auth.client.config.ServiceAuthConfig;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yupc
 * @createTime 2017-12-13 15:27
 */
@Configuration
@ComponentScan({"com.github.yupc.auth.client", "com.github.yupc.auth.common.event"})
@RemoteApplicationEventScan(basePackages = "com.github.yupc.auth.common.event")
public class AutoConfiguration {
    @Bean
    ServiceAuthConfig getServiceAuthConfig() {
        return new ServiceAuthConfig();
    }

    @Bean
    AppAuthConfig getUserAuthConfig() {
        return new AppAuthConfig();
    }
}