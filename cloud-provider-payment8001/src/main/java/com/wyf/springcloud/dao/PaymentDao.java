package com.wyf.springcloud.dao;

import com.wyf.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangyifan
 */
@Mapper
public interface PaymentDao {
    /**
     * 创建订单
     * @param payment 订单对象
     * @return 结果
     */
    public int create(Payment payment);

    /**
     * 根据Id查询
     * @param id 订单id
     * @return 订单对象
     */
    public Payment getPaymentById(@Param("id") Long id);
}