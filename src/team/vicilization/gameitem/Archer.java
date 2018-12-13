package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Archer extends Fighter {
    public Archer(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.ARCHER);
    }
}
