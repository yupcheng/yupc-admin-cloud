package com.github.yupc.center.config;

import com.github.yupc.utils.SpringUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 初始化open server 容器
 *
 * @author yupc
 * @createTime 2017-12-25 16:31
 */
@Component
public class CenterInitializer implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.setApplicationContext(applicationContext);
    }
}
