package team.vicilization.country;


import team.vicilization.gameitem.city.CityName;
import team.vicilization.mechanics.leader.LeaderName;
import team.vicilization.util.Position;

import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

public class CountryConfig {
    // 记录默认城市领土
    public static final Vector<Position> DEFAULT_TERRITORY = new Vector<>() {
        {
            add(new Position(1, 0));
            add(new Position(-1, 0));
            add(new Position(0, 1));
            add(new Position(0, -1));

            add(new Position(1, 1));
            add(new Position(-1, 1));
            add(new Position(1, -1));
            add(new Position(-1, -1));

            add(new Position(2, 0));
            add(new Position(0, 2));
            add(new Position(-2, 0));
            add(new Position(0, -2));

            add(new Position(2, 1));
            add(new Position(1, 2));
            add(new Position(-1, 2));
            add(new Position(-2, 1));
            add(new Position(1, -2));
            add(new Position(2, -1));
            add(new Position(-1, -2));
            add(new Position(-2, -1));
        }
    };

    // 国家配色
    public static final HashMap<CountryName, Color> COLOR_OF_COUNTRY = new HashMap<>() {
        {
            put(CountryName.INDIA, Color.PINK);
            put(CountryName.AMERICA, Color.MAGENTA);
        }
    };

    // 记录国家领导人
    public static final HashMap<CountryName, LeaderName> LEADER_OF_COUNTRY = new HashMap<>() {
        {
            put(CountryName.INDIA, LeaderName.GANDHI);
            put(CountryName.AMERICA, LeaderName.ROOSEVELT);
        }
    };

    // 一个国家所能拥有的城市名
    public static final HashMap<CountryName, Vector> CITIES_OF_COUNTRY = new HashMap<>() {
        {
            put(CountryName.INDIA, new Vector<CityName>() {
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
            put(CountryName.AMERICA, new Vector<CityName>() {
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

    // 私有化构造函数以免出现意外的对象
    private CountryConfig() {
    }
}
