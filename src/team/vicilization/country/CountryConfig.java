package team.vicilization.country;

import java.awt.*;
import java.util.HashMap;

public class CountryConfig {
    public static final HashMap<CountryName, Color> COLOR_OF_COUNTRY = new HashMap<CountryName, Color>() {
        {
            put(CountryName.INDIA, Color.PINK);
            put(CountryName.MACEDONIA, Color.GREEN);
        }
    };

    private CountryConfig() {
    }
}
