package team.vicilization.mechanics;

import java.util.HashMap;

public class ScienceConfig {

    private ScienceConfig(){
    }

    public static final HashMap<ScienceName, ScienceName> PRE_SCIENCE = new HashMap<ScienceName, ScienceName>(){
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

    public static final HashMap<ScienceName, ScienceName> NEXT_SCIENCE = new HashMap<ScienceName, ScienceName>(){
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

    public static final HashMap<ScienceName, Integer> SCIENCE_COST = new HashMap<ScienceName, Integer>(){
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
