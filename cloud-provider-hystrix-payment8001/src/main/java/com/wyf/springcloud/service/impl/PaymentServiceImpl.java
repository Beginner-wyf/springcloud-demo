package com.wyf.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wyf.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyifan
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     * 正常访问的接口，一切OK
     *
     * @param id id
     * @return 结果
     */
    @Override
    public String paymentInfoOK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_OK,id: " + id + "\t" + "O(∩_∩)O";
    }

    /**
     * 超时访问，演示降级
     *
     * @param id id
     * @return 结果
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String paymentInfoTimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_TimeOut,id: " + id + "\t" + "O(∩_∩)O，耗费3秒";
    }
    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_TimeOutHandler,服务降级,id: " + id ;
    }

    /**
     * 服务熔断测试
     * circuitBreaker.enabled:开启服务熔断功能
     * circuitBreaker.requestVolumeThreshold：请求数阈值
     * circuitBreaker.sleepWindowInMilliseconds：快照时间窗口时长
     * circuitBreaker.errorThresholdPercentage：错误百分比阈值
     * @param id id
     * @return 结果
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}