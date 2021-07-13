package com.wyf.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wyf.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangyifan
 */
@Slf4j
@RestController
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    /*@HystrixCommand
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id) {
        //制造错误看看是否会调用服务降级
        int i = 1/0;
        return paymentHystrixService.paymentInfoOK(id);
    }*/

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoOK(id);
    }

    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoTimeOut(id);
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return Thread.currentThread().getName()+" 消费端服务降级";
    }

    /**
     * 全局默认降级方法
     * @return 结果
     */
    public String paymentGlobalFallbackMethod() {
        return Thread.currentThread().getName()+" 消费端全局服务降级方法";
    }
}