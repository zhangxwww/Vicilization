package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Explorer extends Folk{

    public Explorer(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.EXPLORER);
    }
}
