package team.vicilization.gameitem.unit;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;


public class Footman extends Fighter {
    public Footman(Position position, Country country) {
        super(position, country, UnitSubType.FOOTMAN);
        setSubType(UnitSubType.FOOTMAN);

    }
}
