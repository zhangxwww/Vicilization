package team.vicilization.gameitem;

public enum UnitSubType {

    NONE,
    EXPLORER,
    CONSTRUCTOR,
    SPEARMAN,
    KNIGHT,
    FOOTMAN,
    ARCHER;

    @Override
    public String toString() {
        String name=super.toString().toLowerCase();
        return name;
    }
}
