package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Footman extends Fighter {
    public Footman(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.FOOTMAN);
    }
}
