package team.vicilization.mechanics;

public enum ScienceName {
    NONE,
    ARITHMETIC,
    GEOMETRY,
    ALGEBRA,
    CALCULUS,
    STOCHASTIC_PROCESS,
    COMPLEX_ANALYSIS,
    FUNCTIONAL_ANALYSIS,
    AEROSPACE;

    @Override
    public String toString() {
        String name = super.toString().toLowerCase();
        name = name.replace('_', ' ');
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
