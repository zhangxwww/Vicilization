package team.vicilization.gameitem;


import team.vicilization.country.Country;
import team.vicilization.util.Position;

public abstract class Folk extends Unit {

    public Folk(Position position, Country country) {
        super(position, country);
        setType(UnitType.FOLK);
    }
}
