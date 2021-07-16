package com.wyf.springcloud.controller;

import com.wyf.springcloud.entities.CommonResult;
import com.wyf.springcloud.entities.Payment;
import com.wyf.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangyifan
 */
@RestController
public class OrderFeignController {

    /**
     * 通过OpenFeign去调用远程服务，不在使用RestTemplate
     */
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 请求延时接口，openfeign的默认等待时间为1s
     * 超过1s的请求会直接报错，若有需要可以另外配置等待时长
     * @return 结果
     */
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut() {
        return paymentFeignService.paymentFeignTimeOut();
    }

    /**
     * 测试zipkin
     * @return 结果
     */
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        return paymentFeignService.paymentZipkin();
    }
}