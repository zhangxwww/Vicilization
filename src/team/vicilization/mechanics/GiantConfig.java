package team.vicilization.mechanics;

import team.vicilization.util.Property;

import java.util.HashMap;

public class GiantConfig {
    public static final HashMap<GiantName, GiantType> GIANT_NAME_TO_TYPE = new HashMap<GiantName, GiantType>() {
        {
            put(GiantName.ALBERT_EINSTEIN, GiantType.SCIENTIST);
            put(GiantName.ERWIN_SCHRODINGER, GiantType.SCIENTIST);
            put(GiantName.ALAN_TURING, GiantType.SCIENTIST);
            put(GiantName.ADAM_SMITH, GiantType.ECONOMIST);
            put(GiantName.DAVID_RICARDO, GiantType.ECONOMIST);
            put(GiantName.JOHN_KEYNES, GiantType.ECONOMIST);
            put(GiantName.LEONARDO_DA_VINCI, GiantType.ENGINEER);
            put(GiantName.GUSTAVE_EIFFEL, GiantType.ENGINEER);
            put(GiantName.NICOLA_TESLA, GiantType.ENGINEER);
            put(GiantName.JAMES_WATT, GiantType.ENGINEER);
        }
    };

    public static final HashMap<GiantType, Property> GIANT_TYPE_BONUS = new HashMap<GiantType, Property>() {
        {
            put(GiantType.ECONOMIST, new Property(0, 100, 0, 0, 0, 0, 0));
            put(GiantType.SCIENTIST, new Property(0, 0, 0, 100, 0, 0, 0));
            put(GiantType.ENGINEER, new Property(100, 0, 0, 0, 0, 0, 0));
        }
    };

    public static final HashMap<GiantType, Property> GIANT_TYPE_COST = new HashMap<GiantType, Property>() {
        {
            put(GiantType.ECONOMIST, new Property(0, 0, 0, 0, 0, 50, 0));
            put(GiantType.SCIENTIST, new Property(0, 0, 0, 0, 50, 0, 0));
            put(GiantType.ENGINEER, new Property(0, 0, 0, 0, 0, 0, 50));
        }
    };

    private GiantConfig() {
    }
}
