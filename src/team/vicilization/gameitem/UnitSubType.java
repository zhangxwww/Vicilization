package team.vicilization.gameitem;

public enum UnitSubType {

    NONE,
    EXPLORER,
    CONSTRUCTOR,
    SPEARMAN,
    KNIGHT,
    FOOTMAN,
    ARCHER,
    SCOUT,
    SWORDSMAN;

    @Override
    public String toString() {
        String name=super.toString().toLowerCase();
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
