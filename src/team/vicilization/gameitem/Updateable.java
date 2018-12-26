package team.vicilization.gameitem;

import team.vicilization.country.Country;

public interface Updateable {
    boolean calculateCanUpdate(Country country);

    boolean isUpgradable();
}
