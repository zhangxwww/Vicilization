package team.vicilization.gameitem;

import team.vicilization.country.Country;

import team.vicilization.util.Position;
import team.vicilization.gamemap.ResourceType;

public class Archer extends Fighter {
    public Archer(Position position, Country country) {
        super(position, country,UnitSubType.ARCHER);
        setSubType(UnitSubType.ARCHER);
    }
}
