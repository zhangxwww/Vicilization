package team.vicilization.gameitem.unit;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;

public class Spearman extends Fighter {
    public Spearman(Position position, Country country) {
        super(position, country,UnitSubType.SPEARMAN);
        setSubType(UnitSubType.SPEARMAN);

    }
}
