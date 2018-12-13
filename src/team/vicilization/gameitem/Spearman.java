package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Spearman extends Fighter {
    public Spearman(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.SPEARMAN);
    }
}
