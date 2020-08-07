package com.yz.git.sc.product.java8;

import java.util.Date;

/**
 * AA
 *
 * @author xuyang
 * @version 1.0
 * @description
 * @date 2020/8/4 14:24
 */
public class AA {

    private static boolean active = true;


    private void run() {
        System.out.println(active);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (active) {
                    doWork();
                }
            }
        });
    }

    private void stop() {
        active = true;
    }

    private void doWork() {
        System.out.println("doWork " + new Date().toString());
    }

    public static void main(String[] args) {
        new AA().run();

    }
}
 