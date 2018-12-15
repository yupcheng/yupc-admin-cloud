package com.github.yupc.center.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.github.yupc.center.configuration.GlobalVariableProperties.GLOBAL_VARIABLE_PREFIX;


@ConfigurationProperties(prefix = GLOBAL_VARIABLE_PREFIX)
@Data
@NoArgsConstructor
@Configuration
public class GlobalVariableProperties {
    public static final String GLOBAL_VARIABLE_PREFIX = "yupc.variable";

    /** 网关服务的url前缀 */
    private String gateWayUrlPrefix = "http://gateway.yupc.com:9770";
}
