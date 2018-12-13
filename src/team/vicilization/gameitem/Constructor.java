package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public class Constructor extends Folk {
    public Constructor(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.CONSTRUCTOR);
    }
}
