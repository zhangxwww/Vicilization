package team.vicilization.gamemap;

import java.util.HashMap;

class GameMapConfig {
    public static final HashMap<TerrainType, Integer> TERRAIN_MOBILITY_COST;
    public static final HashMap<TerrainType, Integer> TERRAIN_DEFENCE_BUFF;
    public static final HashMap<TerrainType, Integer> TERRAIN_PRODUCTIVITY;
    public static final HashMap<TerrainType, Integer> TERRAIN_MONEY;
    public static final HashMap<TerrainType, Integer> TERRAIN_FOOD;
    public static final HashMap<TerrainType, Integer> TERRAIN_SCIENCE;

    public static final HashMap<LandformType, Integer> LANDFORM_MOBILITY_COST;
    public static final HashMap<LandformType, Integer> LANDFORM_DEFENCE_BUFF;
    public static final HashMap<LandformType, Integer> LANDFORM_PRODUCTIVITY;
    public static final HashMap<LandformType, Integer> LANDFORM_MONEY;
    public static final HashMap<LandformType, Integer> LANDFORM_FOOD;
    public static final HashMap<LandformType, Integer> LANDFORM_SCIENCE;

    public static final HashMap<String, Integer> RESOURCE_MOBILITY_COST;
    public static final HashMap<String, Integer> RESOURCE_DEFENCE_BUFF;
    public static final HashMap<ResourceType, Integer> RESOURCE_PRODUCTIVITY;
    public static final HashMap<ResourceType, Integer> RESOURCE_MONEY;
    public static final HashMap<ResourceType, Integer> RESOURCE_FOOD;
    public static final HashMap<ResourceType, Integer> RESOURCE_SCIENCE;
}
