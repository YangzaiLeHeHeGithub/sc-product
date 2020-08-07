package com.yz.git.sc.product.java8.jihe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * MyCollection
 * 集合
 *
 * @author xuyang
 * @version 1.0
 * @description
 * @date 2020/8/3 13:54
 */
public class MyCollection {
    public static void main(String[] args) {
        colDemo();
    }

    public static void colDemo() {
//        Map map = Maps.newHashMapWithExpectedSize(3);
//        List list = Lists.newArrayListWithExpectedSize(4);
        List<Integer> list1 = Lists.newArrayList(1, 23, 32, 14);
        Collections.sort(list1);
        Object[] a = list1.toArray();
        Arrays.sort(a, (Comparator) null);
        ListIterator<Integer> i = list1.listIterator();
        for (Object e : a) {
            i.next();
            i.set((Integer) e);
        }
        System.out.println(list1.toString());
    }
}
 