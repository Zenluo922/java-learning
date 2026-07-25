package com.atguigu.b_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Demo3 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("唐僧");
        list.add("孙悟空");
        list.add("猪八戒");
        list.add("沙僧");

        //Iterator<String> iterator = list.iterator();
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()){
            String element = listIterator.next();
            if("猪八戒".equals(element)){
                listIterator.add("白龙马");
            }
        }
        System.out.println(list);
    }//实际操作次数和预期操作次数不一样
}
