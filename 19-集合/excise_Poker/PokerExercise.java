package excise_Poker;

import java.util.*;

/**
 * 斗地主洗牌发牌小练习
 *
 * 规则：
 *   54 张牌（52 张普通牌 + 2 张大小王），打乱后三人交替摸牌，
 *   每人 17 张，最后 3 张留作底牌。
 *
 * 涵盖知识点：
 *   1. ArrayList 嵌套循环组装数据
 *   2. Collections.shuffle() 随机打乱
 *   3. 取模发牌（i % 3 分配玩家）
 *   4. 底牌处理（最后 3 张）
 */
public class PokerExercise {

    public static void main(String[] args) {

        // ================================================================
        // 第一步：准备花色
        // ================================================================
        // 创建 ArrayList<String> 存 4 种花色：♠ ♥ ♣ ♦
        // 参考写法：
        //   ArrayList<String> color = new ArrayList<>();
        //   color.add("♠");
        //   color.add("♥");
        //   color.add("♣");
        //   color.add("♦");

        // TODO: 在这里写花色集合 ↓↓↓
        ArrayList<String> color = new ArrayList<>();
        color.add("♠");
        color.add("♥");
        color.add("♦");
        color.add("♣");

        // ================================================================
        // 第二步：准备牌号
        // ================================================================
        // 创建 ArrayList<String> 存牌号：2~10 + J Q K A
        // 提示：2~10 用循环，J/Q/K/A 逐个 add

        // TODO: 在这里写牌号集合 ↓↓↓
        ArrayList<String> number = new ArrayList<>();
        for (int i = 2; i <= 10; i++) {
            number.add(""+i);
        }
        number.add("J");
        number.add("Q");
        number.add("K");
        number.add("A");

        // ================================================================
        // 第三步：组装扑克牌
        // ================================================================
        // 创建 ArrayList<String> poker，用嵌套循环拼接花色+牌号
        // 例如：♠2 ♠3 ... ♦K ♦A
        // 最后加上大小王：😊 ☺

        // TODO: 在这里写组装牌面 ↓↓↓
        ArrayList<String> poker = new ArrayList<>();
        for (String num : number) {
            for (String huaSe : color) {
                String pokerNumber = huaSe + num;
                poker.add(pokerNumber);
            }
        }
        poker.add("😊");
        poker.add("☺");

        // ================================================================
        // 第四步：洗牌
        // ================================================================
        // 用 Collections.shuffle() 随机打乱

        // TODO: 在这里洗牌 ↓↓↓
        Collections.shuffle(poker);

        // ================================================================
        // 第五步：发牌
        // ================================================================
        // 创建 4 个集合：p1 p2 p3 dipai
        // 遍历 poker，根据索引发牌：
        //   i >= 51        → 底牌（最后 3 张）
        //   i % 3 == 0     → 玩家1
        //   i % 3 == 1     → 玩家2
        //   i % 3 == 2     → 玩家3

        // TODO: 在这里写发牌逻辑 ↓↓↓
        ArrayList<String> p1 = new ArrayList<>();
        ArrayList<String> p2 = new ArrayList<>();
        ArrayList<String> p3 = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();
        for (int i = 0; i < poker.size(); i++) {
            String s= poker.get(i);
            if( i>=51){
                dipai.add(s);
            }else if(i%3 ==1){
                p2.add(s);
            }else if(i%3 ==2) {
                p3.add(s);
            }else if(i%3 ==0){
                p1.add(s);
            }
        }


        // ================================================================
        // 第六步：看牌
        // ================================================================

        System.out.println("玩家1: " + p1);
        System.out.println("玩家2: " + p2);
        System.out.println("玩家3: " + p3);
        System.out.println("底牌: " + dipai);
    }
}
