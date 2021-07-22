package com.wyf.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author wangyifan
 */
public class MyDealHandler {
    public static String handler1(String p1, String p2, BlockException exception) {
        return "-----dealHandler_handler1 p1=" + p1 + ",p2=" + p2;
    }
    public static String handler2(String p1, String p2,BlockException exception) {
        return "-----dealHandler_handler2 p1=" + p1 + ",p2=" + p2;
    }
}
