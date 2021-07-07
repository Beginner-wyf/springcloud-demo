package com.wyf.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangyifan
 */
@Configuration
public class ApplicationContextConfig {
    /**
     * 添加进spring容器
     * @return 返回一个RestTemplate对象
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
