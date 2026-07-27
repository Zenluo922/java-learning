package generic;

/**
 * 泛型小练习 —— 三道题，每道只做一件事
 */
public class GenericExercise {

    // ========== 题1：泛型类 ==========
    // 写一个 Box<T> 类，能存一个任意类型的物品
    // 要求：set(T t) 存入，get() 取出
    //
    // 参考写法：
    // public static class Box<T> {
    //     private T item;              // T 类型的成员变量
    //     public void set(T item) { this.item = item; }
    //     public T get() { return item; }
    // }

    // TODO: 在这里写 Box 类 ↓↓↓
    public static class Box<T>{
        private T box;
        public void set(T box){
            this.box = box;
        }
        public T get(){
            return box;
        }



    }

    // ========== 题2：泛型接口 ==========
    // 写一个 Printer<T> 接口，有一个 void print(T t) 方法
    // 然后写一个 StringPrinter 实现它，打印 "打印: xxx"
    //
    // 参考写法：
    // public interface Printer<T> {
    //     void print(T t);
    // }
    // public static class StringPrinter implements Printer<String> {
    //     public void print(String s) { System.out.println("打印: " + s); }
    // }

    // TODO: 在这里写 Printer 接口和 StringPrinter 类 ↓↓↓
    public interface Printer<T>{
        void print(T t);
    }
    public static class StringPrinter implements Printer<String>{

        @Override
        public void print(String s) {
            System.out.println("打印:"+s);
        }
    }

    // ========== 题3：泛型方法 ==========
    // 写一个 static 泛型方法，打印任意类型的数组
    //
    // 参考写法：
    // public static <T> void printArray(T[] arr) {
    //     for (T t : arr) {
    //         System.out.print(t + " ");
    //     }
    //     System.out.println();
    // }

    // TODO: 在这里写 printArray 方法 ↓↓↓
    public static<T> void printArray(T[]arr){
        for (T t : arr) {
            System.out.println(t+"");
        }
    }


    // ========== 验证：写完上面的代码，去掉下面注释运行即可 ==========
    public static void main(String[] args) {
        // 题1验证

        Box<String> box1 = new Box<>();
        box1.set("苹果");
        System.out.println(box1.get());  // 期望：苹果

        Box<Integer> box2 = new Box<>();
        box2.set(123);
        System.out.println(box2.get());  // 期望：123


        // 题2验证

        StringPrinter sp = new StringPrinter();
        sp.print("hello");  // 期望：打印: hello


        // 题3验证

        String[] sArr = {"A", "B", "C"};
        printArray(sArr);   // 期望：A B C

        Integer[] iArr = {1, 2, 3};
        printArray(iArr);   // 期望：1 2 3

    }
}
