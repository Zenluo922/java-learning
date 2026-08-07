package excise_reflect;

/**
 * 英雄实体类 — 供反射练习使用
 *
 * 构造器：
 *   ① public 无参
 *   ② private 单参（name）
 *   ③ public 三参（name, level, skill）
 *
 * 字段：
 *   ① private String name
 *   ② public Integer level
 *   ③ private String skill
 *
 * 方法：
 *   ① public getter/setter（name, level, skill）共 6 个
 *   ② public String toString()
 *   ③ public void fight()       — 公开战斗方法
 *   ④ public void fight(String weapon)  — 重载战斗方法
 *   ⑤ private void train()      — 私有训练方法
 */
public class Hero {
    private String name;
    public Integer level;
    private String skill;

    // ===== 三个构造器 =====
    public Hero() {
    }

    private Hero(String name) {
        this.name = name;
    }

    public Hero(String name, Integer level, String skill) {
        this.name = name;
        this.level = level;
        this.skill = skill;
    }

    // ===== getter / setter =====
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    // ===== toString =====
    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", skill='" + skill + '\'' +
                '}';
    }

    // ===== 业务方法 =====

    /** 公开方法 — 战斗 */
    public void fight() {
        System.out.println(name + "使用" + skill + "进行战斗！");
    }

    /** 公开方法 — 重载战斗（带武器） */
    public void fight(String weapon) {
        System.out.println(name + "挥舞" + weapon + "进行战斗！");
    }

    /** 私有方法 — 偷偷训练 */
    private void train() {
        System.out.println(name + "在偷偷训练，战斗力飙升！");
    }
}
