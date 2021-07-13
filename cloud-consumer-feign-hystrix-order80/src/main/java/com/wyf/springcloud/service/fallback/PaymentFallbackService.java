package com.wyf.springcloud.service.fallback;

import com.wyf.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author wangyifan
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfoOK(Integer id) {
        return Thread.currentThread().getName() + "PaymentFallbackService-paymentInfoOK-fallback"+id;
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return Thread.currentThread().getName() + "PaymentFallbackService-paymentInfoTimeOut-fallback"+id;
    }
}
