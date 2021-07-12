package com.wyf.springcloud.service;

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
    public String paymentInfo_OK(Integer id);

    /**
     * 超时访问，演示降级
     *
     * @param id id
     * @return 结果
     */
    public String paymentInfo_TimeOut(Integer id);

}
