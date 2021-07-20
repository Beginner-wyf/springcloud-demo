package com.wyf.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author wangyifan
 */
@RestController
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    /**
     * 根据nacos中注册的服务名获取具体的URI访问路径
     */
    @Value("${service-url.nacos-user-service}")
    private String SERVER_URL;

    /**
     * 通过resttemplate来远程调用服务
     * @param id 参数
     * @return 结果
     */
    @GetMapping("/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id) {
        return restTemplate.getForObject(SERVER_URL + "/payment/nacos/" + id, String.class);
    }
}