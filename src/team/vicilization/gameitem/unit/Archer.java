package team.vicilization.gameitem.unit;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Archer extends Fighter {
    public Archer(Position position, Country country) {
        super(position, country,UnitSubType.ARCHER);
        setSubType(UnitSubType.ARCHER);
    }
}