package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

import javax.swing.*;

public abstract class Unit extends JButton {
    UnitType type;
    UnitSubType subType;
    Country country;
    Position position;
    int health;
    UnitInfo unitInfo;


    public Unit() {

    }

    public Unit(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void delete(){
        setVisible(false);
        setEnabled(false);
    }
}
