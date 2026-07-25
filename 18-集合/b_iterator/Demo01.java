package com.atguigu.b_iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class Demo01 {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add("萧炎");
        list.add("萧熏儿");
        list.add("彩鳞");
        list.add("云韵");
        list.add("唐三");

        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}
