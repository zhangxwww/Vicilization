package team.vicilization.gameitem;

public enum SelectableSubType {
    CITY,
    FOLK,
    FIGHTER;

    public String toString() {
        String name=super.toString().toLowerCase();
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
