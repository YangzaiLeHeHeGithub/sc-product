package com.yz.git.sc.product.java8.impl;

import com.yz.git.sc.product.java8.MyFunctionInterface;

/**
 * MyFunctionInterface
 *
 * @author xuyang
 * @version 1.0
 * @description 自定义函数式接口实现类
 * @date 2020/7/17 17:45
 */
public class MyFunctionInterfaceImpl implements MyFunctionInterface {
    @Override
    public void method() {
        System.out.println("Impl");
    }
}
 