package team.vicilization.gameitem.unit;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class SwordsMan extends Fighter {
    public SwordsMan(Position position, Country country) {
        super(position, country, UnitSubType.SWORDSMAN);
        setSubType(UnitSubType.SWORDSMAN);
    }
}
