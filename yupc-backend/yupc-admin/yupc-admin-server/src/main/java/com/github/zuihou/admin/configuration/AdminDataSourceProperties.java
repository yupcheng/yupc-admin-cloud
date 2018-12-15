package com.github.yupc.admin.configuration;

/**
 * @author yupc
 * @createTime 2018-01-25 22:48
 */

import com.github.yupc.core.spring.autoconfigure.datasource.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取yml配置的信息
 * @author yupc
 */
@ConfigurationProperties(
        prefix = "yupc.mysql.admin"
)
public class AdminDataSourceProperties extends DataSourceProperties {

}
