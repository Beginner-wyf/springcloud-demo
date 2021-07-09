package com.wyf.myrole;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyifan
 */
@Configuration
public class MySelfRule {
    /**
     * 定义为随机
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}