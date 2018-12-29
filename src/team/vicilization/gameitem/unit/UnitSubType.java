package team.vicilization.gameitem.unit;

public enum UnitSubType {

    NONE,
    EXPLORER,
    CONSTRUCTOR,
    SPEARMAN,
    KNIGHT,
    FOOTMAN,
    ARCHER,
    ASSASSIN,
    SWORDSMAN;

    @Override
    public String toString() {
        String name = super.toString().toLowerCase();
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
