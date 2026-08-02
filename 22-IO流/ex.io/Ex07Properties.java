package ex.io;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * 练习7：第六章 Properties 集合 + 配置文件
 *
 * 知识点覆盖：
 *   1. Properties 基本操作 —— setProperty / getProperty / stringPropertyNames
 *   2. load(InputStream) —— 从 .properties 文件加载配置
 *   3. 配置文件规范 —— key=value，不用引号，# 注释，不建议中文
 *   4. 为什么要用配置文件 —— 数据抽离，改配置不改代码
 *
 * 配套文件：在 D:\Idea\io\ 下创建 jdbc.properties，内容自拟
 *
 * TODO: 补全所有标记处的代码
 */
public class Ex07Properties {
    public static void main(String[] args) throws Exception {
        // ========== 基础操作 ==========
        // TODO 1: 创建 Properties 对象，用 setProperty 存 3 对键值
        // TODO 2: 用 stringPropertyNames() 获取所有 key，遍历打印
        // TODO 3: 重复存同一个 key（如 username），验证新值覆盖旧值
        Properties properties = new Properties();
        properties.setProperty("张无忌","19");
        properties.setProperty("无忌","1");
        properties.setProperty("无忌","9");

        Set<String> set = properties.stringPropertyNames();
        for (String s : set) {
            System.out.println(properties.getProperty(s));
        }

        // ========== 从配置文件加载 ==========
        // TODO 4: 创建 FileInputStream 指向 jdbc.properties，用 load() 加载
        // TODO 5: 遍历打印配置文件中的所有键值对
        Properties properties1 = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");
        properties1.load(fis);
        Set<String> set1 = properties1.stringPropertyNames();
        for (String s : set1) {
            System.out.println(s+"..."+properties1.getProperty(s));
        }
        fis.close();

        // 思考：
        // - Properties 的父类是谁？
        // - 为什么不把用户名密码硬编码在代码里？
    }
}
