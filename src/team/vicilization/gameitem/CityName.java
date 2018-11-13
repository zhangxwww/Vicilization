package team.vicilization.gameitem;

public enum CityName {
    DELHI,
    NEWDELHI,
    BOMBAY,
    CALCUTTA;

    @Override
    public String toString() {
        String name=super.toString().toLowerCase();
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
