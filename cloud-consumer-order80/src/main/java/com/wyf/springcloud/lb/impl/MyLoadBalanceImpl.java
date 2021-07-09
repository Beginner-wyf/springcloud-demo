package com.wyf.springcloud.lb.impl;

import com.wyf.springcloud.lb.MyLoadBalance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangyifan
 * 负载均衡算法实现类，需托管到spring容器中
 */
@Component
public class MyLoadBalanceImpl implements MyLoadBalance {

    /**
     * 创建一个原子类型的整数对象用于存放服务调用次数，用final修饰符保证其唯一性
     */
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 用于为效用次数增加次数并返回下一次要调用是实例下标
     * @return 实例下标
     */
    public final int getAndIncrement(){
        //下一个要获取的实例下标
        int next;
        //当前的实例下标
        int current;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current+1;
            //do..while配合CAS自旋，用于计算下一次的实例下标，
        } while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("当前服务调用次数："+next);
        return next;
    }

    /**
     * 根据自定义负载均衡算法计算并返回要调用的服务实例
     * @param serviceInstances 服务集合
     * @return 服务实例
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
