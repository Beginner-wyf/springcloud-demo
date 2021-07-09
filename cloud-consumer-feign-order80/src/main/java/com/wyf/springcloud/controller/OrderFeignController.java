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
}