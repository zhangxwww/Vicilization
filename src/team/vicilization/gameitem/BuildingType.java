package team.vicilization.gameitem;

public enum BuildingType {

    NONE,
    ACADEMY,
    COMMERCIAL_CERTER,
    INDUSTRIAL_PARK;

    @Override
    public String toString() {
        String name=super.toString().toLowerCase();
        name = name.replace('_', ' ');
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
