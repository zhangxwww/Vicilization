package team.vicilization.gameitem;

import java.util.HashMap;

public class GameItemConfig {
    public static final HashMap<BuildingType,Building> BUILDING_TYPE_TO_BUILDING = null;
    public static final HashMap<UnitSubType, Integer> UNIT_ATTACK = new HashMap<UnitSubType, Integer>(){
        {
            put(UnitSubType.KNIGHT, 4);
        }
    };
}
