package team.vicilization.gameitem;

public enum BuildingType {

    NONE,
    ACADEMY,
    COMMERCIAL_CERTER,
    INDUSTRIAL_PARK;

    @Override
    public String toString() {
        String name=super.toString().toLowerCase();
        name.replace('_', ' ');
        return name;
    }
}
