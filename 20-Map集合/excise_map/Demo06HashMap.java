package excise4_map;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Map集合练习：统计字符串中每一个字符出现的次数
 */
public class Demo06HashMap {
    public static void main(String[] args) {
        //1.创建Scanner和HashMap
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<>();
        //2.遍历字符串,将每一个字符获取出来
        String i = sc.next();
        char[] chars = i.toCharArray();
        //3.判断,map中是否包含遍历出来的字符 -> containsKey
        for (char aChar : chars) {
            String key = aChar+"";//将字符转成字符串
            if (!map.containsKey(key)){
                map.put(key,1);
            }else {
                Integer value = map.get(key);
                value++;
                map.put(key,value);
            }
        }

        //4.如果不包含,证明此字符第一次出现,直接将此字符和1存储到map中

        //5.如果包含,根据字符获取对应的value,让value++

        //6.将此字符和改变后的value重新保存到map集合中

        //7.输出
        System.out.println(map);
    }
}
