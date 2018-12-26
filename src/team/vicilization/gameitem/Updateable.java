package team.vicilization.gameitem;

import team.vicilization.country.Country;

public interface Updateable {
    public boolean calculateCanUpdate(Country country);
}
