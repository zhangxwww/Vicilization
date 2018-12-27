package team.vicilization.gameitem.unit;

import team.vicilization.country.Country;

public interface Updateable {
    boolean calculateCanUpdate(Country country);

    boolean isUpgradable();
}
