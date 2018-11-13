package team.vicilization.gameitem;

public enum UnitSubType {

    EXPLORER,
    CONSTRUCTOR,
    SPEARMAN,
    KNIGHT,
    FOOTMAN,
    ARCHER;

    @Override
    public String toString() {
        String name=super.toString().toLowerCase();
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
