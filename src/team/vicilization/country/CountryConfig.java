package team.vicilization.country;


import team.vicilization.gameitem.CityName;
import team.vicilization.mechanics.LeaderName;

import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

public class CountryConfig {
    public static final HashMap<CountryName, Color> COLOR_OF_COUNTRY = new HashMap<>() {
        {
            put(CountryName.INDIA, Color.PINK);
            put(CountryName.AMERICA, Color.GREEN);
        }
    };

    public static final HashMap<CountryName, LeaderName> LEADER_OF_COUNTRY = new HashMap<>(){
        {
            put(CountryName.INDIA, LeaderName.GANDHI);
            put(CountryName.AMERICA, LeaderName.ALEXANDER);
        }
    };

    public static final HashMap<CountryName, Vector> CITIES_OF_COUNTRY = new HashMap<>(){
        {
            put(CountryName.INDIA, new Vector<CityName>(){
                {
                    add(CityName.BOMBAY);
                    add(CityName.DELHI);
                    add(CityName.NEWDELHI);
                    add(CityName.CALCUTTA);
                    add(CityName.KERALA);
                    add(CityName.TELANGANA);
                    add(CityName.PUNJUB);
                    add(CityName.UTTARAKHAND);
                }
            });
            put(CountryName.AMERICA, new Vector<CityName>(){
                {
                    add(CityName.CHICAGO);
                    add(CityName.COLUMBIA);
                    add(CityName.DALLAS);
                    add(CityName.DETROIT);
                    add(CityName.HAWAII);
                    add(CityName.SANFRANCISCO);
                    add(CityName.WASHINGTON);
                    add(CityName.HARRISBURG);
                }
            });
        }
    };

    private CountryConfig() {
    }
}
