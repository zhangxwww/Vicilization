package team.vicilization.mechanics;

public enum ScienceName {
    NONE,
    ARITHMETIC,
    GEOMETRY,
    ALGEBRA,
    CALCULUS,
    PROBABILITY,
    COMBINATION,
    STATISTICS,
    BUDDHISM;

    @Override
    public String toString() {
        String name = super.toString().toLowerCase();
        name = name.replace('_', ' ');
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
