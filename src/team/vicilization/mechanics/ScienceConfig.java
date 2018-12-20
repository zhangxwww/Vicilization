package team.vicilization.mechanics;

import java.util.HashMap;

public class ScienceConfig {

    private ScienceConfig(){
    }

    public static final HashMap<ScienceName, ScienceName> PRE_SCIENCE = new HashMap<ScienceName, ScienceName>(){
        {
            put(ScienceName.MATH, null);
            put(ScienceName.CALCULUS, ScienceName.MATH);
        }
    };

    public static final HashMap<ScienceName, ScienceName> NEXT_SCIENCE = new HashMap<ScienceName, ScienceName>(){
        {
            put(ScienceName.MATH, ScienceName.CALCULUS);
            put(ScienceName.CALCULUS, null);
        }
    };

    public static final HashMap<ScienceName, Integer> SCIENCE_COST = new HashMap<ScienceName, Integer>(){
        {
            put(ScienceName.MATH, 200);
            put(ScienceName.CALCULUS, 1000);
        }
    };
}
