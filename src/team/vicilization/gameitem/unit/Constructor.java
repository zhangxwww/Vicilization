package team.vicilization.gameitem.unit;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Constructor extends Folk {
    private int times;

    public Constructor(Position position, Country country) {
        super(position, country, UnitSubType.CONSTRUCTOR);
        setSubType(UnitSubType.CONSTRUCTOR);
        this.times = 3;
    }

    public int getTimes() {
        return times;
    }

    public void reduceTimes() {
        this.times -= 1;
        if (this.times <= 0) {
            this.country.deleteUnit(this);
        }
    }
}
