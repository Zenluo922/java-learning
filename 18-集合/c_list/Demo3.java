package com.atguigu.c_list;

import java.util.ArrayList;

public class Demo3 {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        
        //integers.remove(new Integer(2));
        integers.remove(Integer.valueOf(2));
        System.out.println("integers = " + integers);


    }
}
