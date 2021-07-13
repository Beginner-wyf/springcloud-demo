package com.wyf.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangyifan
 */
public interface PaymentService {

    /**
     * 正常访问的接口，一切OK
     *
     * @param id id
     * @return 结果
     */
    public String paymentInfoOK(Integer id);

    /**
     * 超时访问，演示降级
     *
     * @param id id
     * @return 结果
     */
    public String paymentInfoTimeOut(Integer id);

    /**
     * 服务熔断测试
     * @param id id
     * @return 结果
     */
    public String paymentCircuitBreaker(@PathVariable("id") Integer id);

}
