package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Scout extends Fighter{
    public Scout(Position position, Country country) {
        super(position, country,UnitSubType.SCOUT);
        setSubType(UnitSubType.SCOUT);
    }
}
