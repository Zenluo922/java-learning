package com.atguigu.c_list;

import java.util.ArrayList;

public class Demo01ArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        //boolean add(E e)  -> 将元素添加到集合中->尾部(add方法一定能添加成功的,所以我们不用boolean接收返回值)
        list.add("张无忌");
        list.add("无忌");
        list.add("忌");
        System.out.println(list);
        //void add(int index, E element) ->在指定索引位置上添加元素
        list.add(2,"周芷若");
        System.out.println(list);
        //boolean remove(Object o) ->删除指定的元素,删除成功为true,失败为false

        //E remove(int index) -> 删除指定索引位置上的元素,返回的是被删除的那个元素

        //E set(int index, E element) -> 将指定索引位置上的元素,修改成后面的element元素
        String set = list.set(1, "张翠山");
        System.out.println(set);
        System.out.println(list);
        //E get(int index) -> 根据索引获取元素
        System.out.println(list.get(0));
        //int size()  -> 获取集合元素个数
        System.out.println(list.size());
    }
}
