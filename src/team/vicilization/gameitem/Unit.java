package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

import javax.swing.*;

public abstract class Unit {
    protected UnitType type;
    protected UnitSubType subType;
    protected Country country;
    protected Position position;
    protected int health;
    protected UnitInfo unitInfo;


    public Unit() {

    }

    public Unit(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void delete() {
    }

    public UnitType getType() {
        return type;
    }

    public UnitSubType getSubType() {
        return subType;
    }

    public void setType(UnitType type) {
        this.type = type;
    }

    public void setSubType(UnitSubType subType) {
        this.subType = subType;
    }
}
