package com.wyf.springcloud.service;

import com.wyf.springcloud.entities.Payment;

/**
 * @author wangyifan
 */
public interface PaymentService {
    /**
     * 创建订单
     *
     * @param payment 订单对象
     * @return 结果
     */
    public int create(Payment payment);

    /**
     * 根据Id查询
     *
     * @param id 订单id
     * @return 订单对象
     */
    public Payment getPaymentById(Long id);
}
 
