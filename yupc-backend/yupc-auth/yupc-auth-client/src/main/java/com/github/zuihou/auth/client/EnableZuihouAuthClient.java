package com.github.yupc.auth.client;

import com.github.yupc.auth.client.configuration.AutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用授权client
 * @author yupc
 * @createTime 2017-12-13 15:26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfiguration.class)
@Documented
@Inherited
public @interface EnableyupcAuthClient {
}
