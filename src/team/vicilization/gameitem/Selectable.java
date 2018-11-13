package team.vicilization.gameitem;

public interface Selectable {
    boolean selected = false;
    SelectableType type = SelectableType.CITY;
    SelectableSubType subType = SelectableSubType.CITY;

    boolean ableToSelect();
    void setAbleToSelect(boolean able);
}
