package team.vicilization.gameitem;

public enum UnitType {
    FOLK,
    FIGHTER;

    @Override
    public String toString() {
        String name=super.toString().toLowerCase();
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
