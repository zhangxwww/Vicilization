package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public abstract class Fighter extends Unit {


    public Fighter(Position position, Country country,UnitSubType unitSubType) {
        super(position, country,UnitType.FIGHTER,unitSubType);
        setType(UnitType.FIGHTER);

    }
}
