package com.yz.git.sc.product.java8;

import com.yz.git.sc.product.java8.impl.MyFunctionInterfaceImpl;

/**
 * Train
 *
 * @author xuyang
 * @version 1.0
 * @description
 * @date 2020/7/17 17:48
 */
public class Train {
    public static void show(MyFunctionInterface myFunctionInterface){
        myFunctionInterface.method();
    }
    public static void main(String[] args) {
        show(new MyFunctionInterfaceImpl());
        show(new MyFunctionInterface() {
            @Override
            public void method() {
                System.out.println("匿名内部类");
            }
        });
        show(() -> {System.out.println("Lamada带有大括号");});
        show(() -> System.out.println("Lamada"));
    }
}
 