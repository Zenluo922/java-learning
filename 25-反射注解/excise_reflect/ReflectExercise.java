package excise_reflect;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 反射综合练习 — 涵盖 module25 全部反射知识点
 *
 * 知识点清单：
 *   ① Class 对象的三种获取方式
 *   ② 动态加载（Class.forName）
 *   ③ 构造器反射：getConstructors / getConstructor / getDeclaredConstructors / getDeclaredConstructor / setAccessible
 *   ④ 方法反射：getMethods / getMethod / getDeclaredMethods / getDeclaredMethod / invoke / setAccessible
 *   ⑤ 字段反射：getFields / getField / getDeclaredFields / getDeclaredField / get / set / setAccessible
 *   ⑥ 综合实战：properties 配置文件 → 反射加载 → 实例化 → 调用方法 → 注入字段
 */
public class ReflectExercise {

    // ====================================================================
    // 题1：获取 Class 对象的三种方式
    // ====================================================================
    //
    // 要求：在 method1() 中用三种不同的方式获取 Hero 类的 Class 对象，
    //       分别打印出来，并验证三个对象是否相等（== 比较）。
    //
    // 提示：
    //   ① 通过对象调用 getClass()
    //   ② 通过类名.class
    //   ③ 通过 Class.forName("完整包名.类名")
    //
    // 预期输出：
    //   三个 Class 对象应该完全相等（同一个类只有一个 Class 对象）
    //
    // ====================================================================

    // TODO: 在这里写 method1 ↓↓↓
    private static void method1() throws Exception {
        // ① 通过对象.getClass()
        Hero hero = new Hero();
        Class<? extends Hero> aClass = hero.getClass();
        System.out.println("① getClass()  : " + aClass);

        // ② 通过类名.class
        Class<Hero> aClass1 = Hero.class;
        System.out.println("② 类名.class  : " + aClass1);

        // ③ 通过 Class.forName("包名.类名")
        Class<?> aClass2 = Class.forName("excise_reflect.Hero");
        System.out.println("③ forName()   : " + aClass2);

        // 验证三个对象是否相等
        System.out.println("三个都相等？ " + (aClass == aClass1 && aClass1 == aClass2));
    }


    // ====================================================================
    // 题2：构造器反射（5 个小问）
    // ====================================================================

    // ----- 题2a：获取所有 public 构造器 -----
    //
    // 要求：用 getConstructors() 获取 Hero 所有 public 构造器，打印出来。
    //       应该输出 2 个：无参构造器 + 三参构造器。
    //
    // ====================================================================

    // TODO: 在这里写 method2a ↓↓↓
    private static void method2a() throws Exception {
        Class<Hero> aClass = Hero.class;
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

    }


    // ----- 题2b：获取无参构造器并创建对象 -----
    //
    // 要求：用 getConstructor()（不传参数）获取无参构造器，
    //       然后用 newInstance() 创建一个 Hero 对象，打印对象。
    //
    // 预期输出：Hero{name='null', level=null, skill='null'}
    //
    // ====================================================================

    // TODO: 在这里写 method2b ↓↓↓
    private static void method2b() throws Exception {
        Class<Hero> aClass = Hero.class;
        Constructor<Hero> constructor = aClass.getConstructor();
        Hero newInstance = constructor.newInstance();
        System.out.println(newInstance);

    }


    // ----- 题2c：获取有参构造器并传参创建对象 -----
    //
    // 要求：用 getConstructor(String.class, Integer.class, String.class)
    //       获取三参构造器，newInstance 传入 "亚索", 18, "狂风绝息斩"
    //       创建 Hero 对象并打印。
    //
    // 预期输出：Hero{name='亚索', level=18, skill='狂风绝息斩'}
    //
    // ====================================================================

    // TODO: 在这里写 method2c ↓↓↓
    private static void method2c() throws Exception {
        Class<Hero> aClass = Hero.class;
        Constructor<Hero> constructor = aClass.getConstructor(String.class, Integer.class, String.class);
        Hero hero = constructor.newInstance("亚索", 18, "狂风绝息斩");
        System.out.println(hero);

    }


    // ----- 题2d：获取所有构造器（包括私有） -----
    //
    // 要求：用 getDeclaredConstructors() 获取 Hero 所有构造器，打印出来。
    //       应该输出 3 个：无参 + 私有单参 + 三参。
    //       和题2a对比，看看多了哪个？
    //
    // ====================================================================

    // TODO: 在这里写 method2d ↓↓↓
    private static void method2d() throws Exception {
        Class<Hero> aClass = Hero.class;
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

    }


    // ----- 题2e：暴力反射 — 调用私有构造器 -----
    //
    // 要求：用 getDeclaredConstructor(String.class) 获取私有的单参构造器，
    //       用 setAccessible(true) 暴力破解，传入 "疾风剑豪" 创建对象并打印。
    //
    // 提示：
    //   - getDeclaredConstructor(String.class) — 获取接受一个 String 参数的构造器
    //   - constructor.setAccessible(true) — 暴力破解私有权限
    //   - constructor.newInstance("疾风剑豪") — 传入参数创建对象
    //
    // ====================================================================

    // TODO: 在这里写 method2e ↓↓↓
    private static void method2e() throws Exception {
        Class<Hero> aClass = Hero.class;
        Constructor<Hero> declaredConstructor = aClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Hero hero = declaredConstructor.newInstance("疾风剑豪");
        System.out.println("hero = " + hero);

    }


    // ====================================================================
    // 题3：方法反射（4 个小问）
    // ====================================================================

    // ----- 题3a：获取所有 public 方法（包含继承的） -----
    //
    // 要求：用 getMethods() 获取 Hero 的所有 public 方法，遍历打印。
    //       注意：getMethods() 会返回父类 Object 的方法（wait, notify, toString 等），
    //       所以数量会比 Hero 自己定义的要多！
    //
    // ====================================================================

    // TODO: 在这里写 method3a ↓↓↓
    private static void method3a() throws Exception {
        Class<Hero> aClass = Hero.class;
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }


    // ----- 题3b：获取指定 public 方法并调用 -----
    //
    // 要求：
    //   ① 先创建一个 Hero("金克丝", 12, "超究极死神飞弹")
    //   ② 用 getMethod("fight") 获取无参 fight 方法，invoke 调用
    //   ③ 用 getMethod("fight", String.class) 获取带武器参数的 fight，invoke 传入 "鱼骨头火箭筒"
    //   ④ 用 getMethod("getName") + invoke 获取英雄名字并打印
    //
    // 提示：
    //   - 无参方法：getMethod("方法名") → invoke(对象)
    //   - 有参方法：getMethod("方法名", 参数类型.class) → invoke(对象, 实参)
    //   - 有返回值：invoke() 会返回 Object，强转即可
    //
    // ====================================================================

    // TODO: 在这里写 method3b ↓↓↓
    private static void method3b() throws Exception {
        Class<Hero> aClass = Hero.class;
        Constructor<Hero> constructor = aClass.getConstructor(String.class, Integer.class, String.class);
        Hero hero = constructor.newInstance("金克丝", 12, "超究极死神飞弹");
        Method fight = aClass.getMethod("fight");
        fight.invoke(hero);
        Method fight1 = aClass.getMethod("fight", String.class);
        fight1.invoke(hero, "鱼骨头火箭筒");
        Method getName = aClass.getMethod("getName");
        Object invoke = getName.invoke(hero);
        System.out.println(invoke);
    }


    // ----- 题3c：获取本类所有方法（不含继承） -----
    //
    // 要求：用 getDeclaredMethods() 获取 Hero 自己声明的所有方法
    //       （包括 private），遍历打印。和题3a对比，看看少了哪些、多了哪些？
    //
    // ====================================================================

    // TODO: 在这里写 method3c ↓↓↓
    private static void method3c() throws Exception {
        Class<Hero> aClass = Hero.class;
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declaredMethod = " + declaredMethod);
        }
    }


    // ----- 题3d：暴力反射 — 调用私有方法 -----
    //
    // 要求：
    //   ① 创建一个 Hero("盲僧", 15, "猛龙摆尾")
    //   ② 用 getDeclaredMethod("train") 获取私有 train() 方法
    //   ③ setAccessible(true) 暴力破解
    //   ④ invoke 调用
    //
    // 预期输出：盲僧在偷偷训练，战斗力飙升！
    //
    // ====================================================================

    // TODO: 在这里写 method3d ↓↓↓
    private static void method3d() throws Exception {
        Class<Hero> aClass = Hero.class;
        Constructor<Hero> constructor = aClass.getConstructor(String.class, Integer.class, String.class);
        Hero hero = constructor.newInstance("盲僧", 15, "猛龙摆尾");
        Method train = aClass.getDeclaredMethod("train");
        train.setAccessible(true);
        train.invoke(hero);

    }


    // ====================================================================
    // 题4：字段反射（4 个小问）
    // ====================================================================

    // ----- 题4a：获取所有 public 字段 -----
    //
    // 要求：用 getFields() 获取 Hero 的所有 public 字段，打印出来。
    //       应该只有 level 一个（因为 name 和 skill 是 private 的）。
    //
    // ====================================================================

    // TODO: 在这里写 method4a ↓↓↓
    private static void method4a() throws Exception {
        Class<Hero> aClass = Hero.class;
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }


    // ----- 题4b：获取指定 public 字段并读写 -----
    //
    // 要求：
    //   ① 创建一个 Hero("盖伦", 5, "德玛西亚正义")
    //   ② 用 getField("level") 获取 level 字段
    //   ③ 用 set() 把 level 改成 99
    //   ④ 用 get() 读取 level 的值并打印
    //   ⑤ 打印整个 Hero 对象，确认 level 真的变了
    //
    // ====================================================================

    // TODO: 在这里写 method4b ↓↓↓
    private static void method4b() throws Exception {
        Class<Hero> aClass = Hero.class;
        // ① 创建 Hero("盖伦", 5, "德玛西亚正义")
        Constructor<Hero> constructor = aClass.getConstructor(String.class, Integer.class, String.class);
        Hero hero = constructor.newInstance("盖伦", 5, "德玛西亚正义");

        // ② 获取 level 字段
        Field level = aClass.getField("level");

        // ③ set() 把 level 改成 99
        level.set(hero, 99);

        // ④ get() 读取 level 的值并打印
        Object value = level.get(hero);
        System.out.println("level 的值：" + value);

        // ⑤ 打印整个 Hero，确认 level 变了
        System.out.println(hero);
    }


    // ----- 题4c：获取所有字段（包括私有） -----
    //
    // 要求：用 getDeclaredFields() 获取 Hero 所有字段，遍历打印。
    //       应该输出 3 个：name, level, skill。
    //       和题4a对比，getFields vs getDeclaredFields 的区别。
    //
    // ====================================================================

    // TODO: 在这里写 method4c ↓↓↓
    private static void method4c() throws Exception {
        Class<Hero> aClass = Hero.class;
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

    }


    // ----- 题4d：暴力反射 — 读写私有字段 -----
    //
    // 要求：
    //   ① 用 Hero 的无参构造器创建一个空对象
    //   ② 用 getDeclaredField("name") 获取 private name 字段
    //   ③ setAccessible(true) 暴力破解
    //   ④ set() 写入 "瑞雯"
    //   ⑤ 同样暴力破解 private skill 字段，写入 "放逐之锋"
    //   ⑥ get() 读取 name 和 skill 的值并打印
    //   ⑦ 打印 Hero 对象，确认字段真的写入成功了
    //
    // ====================================================================

    // TODO: 在这里写 method4d ↓↓↓
    private static void method4d() throws Exception {
        Class<Hero> aClass = Hero.class;
        // ① 无参构造器创建空对象
        Constructor<Hero> constructor = aClass.getConstructor();
        Hero hero = constructor.newInstance();

        // ②③④ 暴力破解 name 字段并写入
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(hero, "锐雯");   // set(目标对象, 值) — 第一个参数是 hero！

        // ⑤ 暴力破解 skill 字段并写入
        Field skill = aClass.getDeclaredField("skill");
        skill.setAccessible(true);
        skill.set(hero, "放逐之锋");

        // ⑥ 读取并打印
        Object o = name.get(hero);
        System.out.println("name = " + o);
        Object o1 = skill.get(hero);
        System.out.println("skill = " + o1);

        // ⑦ 打印完整对象
        System.out.println(hero);
    }


    // ====================================================================
    // 题5：综合实战 — 模拟 Spring 容器（配置文件驱动）
    // ====================================================================
    //
    // 要求：
    //
    //   a) 在 resources 目录下创建 app.properties，内容为：
    //        className=excise_reflect.Hero
    //        methodName=fight
    //        fieldName=name
    //        fieldValue=影流之主
    //
    //   b) 用 ClassLoader 读取 app.properties（参考 d_reflect/Demo01）：
    //        InputStream is = ReflectExercise.class.getClassLoader()
    //                              .getResourceAsStream("app.properties");
    //        Properties pro = new Properties();
    //        pro.load(is);
    //
    //   c) 从 Properties 中获取 className 和 methodName
    //
    //   d) 用 Class.forName() 加载类
    //
    //   e) 用 newInstance() 创建对象
    //
    //   f) 用 getDeclaredField() 获取 fieldName 字段，setAccessible 后写入 fieldValue
    //
    //   g) 用 getMethod() 获取 methodName 方法，invoke 调用
    //
    //   h) 打印最终的对象
    //
    // 提示：
    //   这就是 Spring 框架最底层的工作原理——把"写死在代码里的 new 和调用"
    //   变成"从配置文件读、用反射动态执行"，从而实现解耦！
    //
    // ====================================================================

    // TODO: 在这里写 method5 ↓↓↓
    private static void method5() throws Exception {
        // ===== 第1步：读取配置文件 =====
        Properties properties = new Properties();
        InputStream is = Hero.class.getClassLoader().getResourceAsStream("app.properties");
        properties.load(is);

        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        String fieldName = properties.getProperty("fieldName");    // 要注入的字段名
        String fieldValue = properties.getProperty("fieldValue");  // 要注入的值

        System.out.println("读取配置 → 类:" + className + "  方法:" + methodName
                + "  字段:" + fieldName + "  值:" + fieldValue);

        // ===== 第2步：反射加载类 + 实例化 =====
        Class<?> aClass = Class.forName(className);
        Object obj = aClass.newInstance();

        // ===== 第3步：注入私有字段（模拟 Spring 的 @Value 注入） =====
        Field field = aClass.getDeclaredField(fieldName);  // 获取私有字段
        field.setAccessible(true);                          // 暴力破解
        field.set(obj, fieldValue);                         // 注入值

        // ===== 第4步：调用方法（模拟 Spring 的 init-method） =====
        Method method = aClass.getMethod(methodName);
        method.invoke(obj);

        // ===== 第5步：打印最终对象，确认字段已注入 =====
        System.out.println("最终对象 → " + obj);
    }


    // ========== 验证入口 ==========
    public static void main(String[] args) throws Exception {
        System.out.println("========== 题1：获取 Class 对象的三种方式 ==========");
        method1();

        System.out.println("\n========== 题2a：获取所有 public 构造器 ==========");
        method2a();

        System.out.println("\n========== 题2b：无参构造器创建对象 ==========");
        method2b();

        System.out.println("\n========== 题2c：有参构造器传参创建 ==========");
        method2c();

        System.out.println("\n========== 题2d：获取所有构造器（含私有） ==========");
        method2d();

        System.out.println("\n========== 题2e：暴力反射私有构造器 ==========");
        method2e();

        System.out.println("\n========== 题3a：获取所有 public 方法（含继承） ==========");
        method3a();

        System.out.println("\n========== 题3b：获取指定 public 方法并调用 ==========");
        method3b();

        System.out.println("\n========== 题3c：获取本类所有方法（不含继承） ==========");
        method3c();

        System.out.println("\n========== 题3d：暴力反射私有方法 ==========");
        method3d();

        System.out.println("\n========== 题4a：获取所有 public 字段 ==========");
        method4a();

        System.out.println("\n========== 题4b：获取指定 public 字段并读写 ==========");
        method4b();

        System.out.println("\n========== 题4c：获取所有字段（含私有） ==========");
        method4c();

        System.out.println("\n========== 题4d：暴力反射读写私有字段 ==========");
        method4d();

        System.out.println("\n========== 题5：综合实战 — 模拟 Spring 容器 ==========");
        method5();
    }
}
