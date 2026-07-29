package excise5_poker;

import java.util.*;

/**
 * 斗地主洗牌发牌 — HashMap 索引映射版
 *
 * 核心思路：
 *   用 HashMap<Integer, String> 建立 "序号 → 牌面" 的映射，
 *   洗牌时只打乱序号列表，发完牌后对序号排序，最后通过序号查牌。
 *
 * 相比直接用 ArrayList<String> 存牌面的好处：
 *   1. 可以通过 Collections.sort() 排序序号，实现理牌效果
 *   2. 牌面与索引解耦，方便扩展（比如修改牌面映射规则）
 */
public class Demo07Poker {
    public static void main(String[] args) {
        //1.创建数组 -> color -> 专门存花色
        String[]color = "♠-♥-♣-♦".split("-");
        //2.创建数组 -> number -> 专门存牌号
        String[]number = "2-3-4-5-6-7-8-9-10-J-Q-K-A".split("-");
        //3.创建map集合,key为序号,value为组合好的牌面
        HashMap<Integer, String> map = new HashMap<>();
        //4.创建一个ArrayList,专门存储key
        //   先把大小王的 key (0,1) 放入 list
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        //5.组合牌,存储到map中
        //   双重循环: 外层牌号, 内层花色 → 生成 52 张普通牌
        //   key 从 2 开始 (0和1已被大小王占用)
        int key = 2;
        for (String num : number) {
            for (String huase : color) {
                String poker = huase+num;
                map.put(key,poker);
                list.add(key);
                key++;
            }
        }
        //   存完普通牌后再把大小王放入 map
        map.put(0,"😊");
        map.put(1,"☺");
        //6.洗牌,打乱list集合中的key
        Collections.shuffle(list);
        //7.创建四个list集合 (三个玩家 + 底牌)
        ArrayList<Integer> p1 = new ArrayList<>();
        ArrayList<Integer> p2 = new ArrayList<>();
        ArrayList<Integer> p3 = new ArrayList<>();
        ArrayList<Integer> dipai = new ArrayList<>();

        //8.发牌
        //   遍历洗牌后的 list:
        //     i >= 51      → 底牌 (最后3张)
        //     i % 3 == 0   → 玩家1
        //     i % 3 == 1   → 玩家2
        //     i % 3 == 2   → 玩家3
        for (int i = 0; i < list.size(); i++) {
            Integer key1 = list.get(i);
            if(i>=51){
                dipai.add(key1);
            } else if (i%3==0) {
                p1.add(key1);
            } else if (i%3==1) {
                p2.add(key1);
            } else if (i%3==2) {
                p3.add(key1);
            }
        }
        //9.排序 (理牌)
        Collections.sort(p1);
        Collections.sort(p2);
        Collections.sort(p3);
        Collections.sort(dipai);

        lookPoker("涛哥",p1,map);
        lookPoker("三上",p2,map);
        lookPoker("金莲",p3,map);
        lookPoker("大郎",dipai,map);
    }

    private static void lookPoker(String name, ArrayList<Integer> list, HashMap<Integer, String> map) {
        // 遍历玩家手中的序号, 通过 map 查找对应牌面并打印
        System.out.print(name+":");
        for (Integer key : list) {
            String value = map.get(key);
            System.out.print(value+" ");
        }
        System.out.println();
    }
}
