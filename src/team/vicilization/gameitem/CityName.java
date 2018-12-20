package team.vicilization.gameitem;

public enum CityName {
    DELHI,
    NEWDELHI,
    BOMBAY,
    CALCUTTA,
    KERALA,
    TELANGANA,
    PUNJUB,
    UTTARAKHAND,
    CHICAGO,
    COLUMBIA,
    DALLAS,
    DETROIT,
    HAWAII,
    SANFRANCISCO,
    WASHINGTON,
    HARRISBURG;

    @Override
    public String toString() {
        String name=super.toString().toLowerCase();
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
