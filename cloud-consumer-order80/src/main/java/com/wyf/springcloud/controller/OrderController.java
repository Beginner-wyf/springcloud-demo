package com.wyf.springcloud.controller;

import com.wyf.springcloud.entities.CommonResult;
import com.wyf.springcloud.entities.Payment;
import com.wyf.springcloud.lb.impl.MyLoadBalanceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author wangyifan
 */
@Slf4j
@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private MyLoadBalanceImpl myLoadBalance;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create/", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") String id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @PostMapping("/consumer/payment/createByEntity")
    public CommonResult createByEntity(@RequestBody Payment payment) {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create/", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return new CommonResult(200, entity.getBody().toString());
        }
        return new CommonResult(444, "操作失败");
    }

    @GetMapping("/consumer/payment/getByEntity/{id}")
    public CommonResult getPaymentByEntity(@PathVariable("id") String id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode().toString() + "  " + entity.getHeaders());
            return entity.getBody();
        }
        return new CommonResult(444, "操作失败");
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        //根据服务名称获取对应的实例集合
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances.isEmpty()) {
            return null;
        }
        //将实例集合穿入自定义的负载均衡算法实现类方法中，用于获取此次应该调用的实例对象
        ServiceInstance serviceInstance = myLoadBalance.instances(instances);
        //根据获取的实例对象来获取http请求地址
        URI uri = serviceInstance.getUri();
        //通过restTemplate访问请求接口
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }


}
