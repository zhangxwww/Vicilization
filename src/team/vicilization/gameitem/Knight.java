package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Knight extends Fighter {
    public Knight(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.KNIGHT);
    }
}
