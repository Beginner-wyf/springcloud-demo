package com.wyf.springcloud.service;

import com.wyf.springcloud.entities.CommonResult;
import com.wyf.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangyifan
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * 获取订单
     * @param id 订单id
     * @return 订单信息
     */
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 请求延时接口
     * @return 延时返回
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut();
}