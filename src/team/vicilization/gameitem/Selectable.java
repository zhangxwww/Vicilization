package team.vicilization.gameitem;

public interface Selectable {
    boolean selected = false;
    SelectableType type = SelectableType.CITY;
    SelectableSubType subType = SelectableSubType.CITY;

    public boolean ableToSelect();

    public void setAbleToSelect(boolean able);
}
