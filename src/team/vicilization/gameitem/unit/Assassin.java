package team.vicilization.gameitem.unit;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Assassin extends Fighter{
    public Assassin(Position position, Country country) {
        super(position, country,UnitSubType.ASSASSIN);
        setSubType(UnitSubType.ASSASSIN);
    }
}
