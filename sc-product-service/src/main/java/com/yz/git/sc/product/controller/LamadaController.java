package com.yz.git.sc.product.controller;

import java.util.Arrays;

/**
 * <p>@ClassName LamadaController<p>
 * <p>@Description Lamada<p>
 * <p>@Author xuYang<p>
 * <p>@Date 2020/6/29 13:07<p>
 */
public class LamadaController {

    public static void main(String[] args) {
        String[] arr = {"z","b"};
        Arrays.sort(arr,String::compareToIgnoreCase);
        System.out.println(Arrays.toString(arr));
        //1111
        //2222
    }

}
