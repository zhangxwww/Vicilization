package team.vicilization.mechanics.science;

import java.util.HashMap;

public class ScienceConfig {

    // 私有化构造函数防止出现意外的对象
    private ScienceConfig() {
    }

    // 记录了每个科技要求的前置科技，由于简化了可技术的实现，因此目前没有用到
    public static final HashMap<ScienceName, ScienceName> PRE_SCIENCE = new HashMap<ScienceName, ScienceName>() {
        {
            put(ScienceName.ARITHMETIC, ScienceName.NONE);
            put(ScienceName.GEOMETRY, ScienceName.ARITHMETIC);
            put(ScienceName.ALGEBRA, ScienceName.GEOMETRY);
            put(ScienceName.CALCULUS, ScienceName.ALGEBRA);
            put(ScienceName.PROBABILITY, ScienceName.CALCULUS);
            put(ScienceName.COMBINATION, ScienceName.PROBABILITY);
            put(ScienceName.STATISTICS, ScienceName.COMBINATION);
            put(ScienceName.BUDDHISM, ScienceName.STATISTICS);
        }
    };

    // 记录了每个科技研究后的下一个科技
    public static final HashMap<ScienceName, ScienceName> NEXT_SCIENCE = new HashMap<ScienceName, ScienceName>() {
        {
            put(ScienceName.NONE, ScienceName.ARITHMETIC);
            put(ScienceName.ARITHMETIC, ScienceName.GEOMETRY);
            put(ScienceName.GEOMETRY, ScienceName.ALGEBRA);
            put(ScienceName.ALGEBRA, ScienceName.CALCULUS);
            put(ScienceName.CALCULUS, ScienceName.PROBABILITY);
            put(ScienceName.PROBABILITY, ScienceName.COMBINATION);
            put(ScienceName.COMBINATION, ScienceName.STATISTICS);
            put(ScienceName.STATISTICS, ScienceName.BUDDHISM);

        }
    };

    // 记录了每个科技消耗的科技值
    public static final HashMap<ScienceName, Integer> SCIENCE_COST = new HashMap<ScienceName, Integer>() {
        {
            put(ScienceName.ARITHMETIC, 20);
            put(ScienceName.GEOMETRY, 40);
            put(ScienceName.ALGEBRA, 80);
            put(ScienceName.CALCULUS, 150);
            put(ScienceName.PROBABILITY, 175);
            put(ScienceName.COMBINATION, 200);
            put(ScienceName.STATISTICS, 300);
            put(ScienceName.BUDDHISM, 400);
        }
    };
}
