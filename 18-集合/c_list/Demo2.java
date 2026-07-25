package com.atguigu.c_list;

import java.util.ArrayList;
import java.util.Iterator;

public class Demo2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("萧炎");
        list.add("萧熏儿");
        list.add("彩鳞");
        list.add("云韵");
        list.add("唐三");

        Iterator<String> iterator = list.iterator();
        while ((iterator.hasNext())){
            System.out.println(iterator.next());
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
