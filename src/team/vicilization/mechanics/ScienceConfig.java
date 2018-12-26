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
            put(ScienceName.STOCHASTIC_PROCESS, ScienceName.CALCULUS);
            put(ScienceName.COMPLEX_ANALYSIS, ScienceName.STOCHASTIC_PROCESS);
            put(ScienceName.FUNCTIONAL_ANALYSIS, ScienceName.COMPLEX_ANALYSIS);
            put(ScienceName.AEROSPACE, ScienceName.FUNCTIONAL_ANALYSIS);
        }
    };

    public static final HashMap<ScienceName, ScienceName> NEXT_SCIENCE = new HashMap<ScienceName, ScienceName>(){
        {
            put(ScienceName.ARITHMETIC, ScienceName.GEOMETRY);
            put(ScienceName.GEOMETRY, ScienceName.ALGEBRA);
            put(ScienceName.ALGEBRA, ScienceName.CALCULUS);
            put(ScienceName.CALCULUS, ScienceName.STOCHASTIC_PROCESS);
            put(ScienceName.STOCHASTIC_PROCESS, ScienceName.COMPLEX_ANALYSIS);
            put(ScienceName.COMPLEX_ANALYSIS, ScienceName.FUNCTIONAL_ANALYSIS);
            put(ScienceName.FUNCTIONAL_ANALYSIS, ScienceName.AEROSPACE);
            put(ScienceName.AEROSPACE, ScienceName.NONE);
        }
    };

    public static final HashMap<ScienceName, Integer> SCIENCE_COST = new HashMap<ScienceName, Integer>(){
        {
            put(ScienceName.ARITHMETIC, 20);
            put(ScienceName.GEOMETRY, 40);
            put(ScienceName.ALGEBRA, 80);
            put(ScienceName.CALCULUS, 150);
            put(ScienceName.STOCHASTIC_PROCESS, 175);
            put(ScienceName.COMPLEX_ANALYSIS, 200);
            put(ScienceName.FUNCTIONAL_ANALYSIS, 300);
            put(ScienceName.AEROSPACE, 400);
        }
    };
}
