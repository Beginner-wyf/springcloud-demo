package com.wyf.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author wangyifan
 */
public interface MyLoadBalance {
    /**
     * 此函数的主要作用是根据轮询算法获取服务实例
     * @param serviceInstances 服务集合
     * @return 轮询算法计算出来要调用的实例
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
