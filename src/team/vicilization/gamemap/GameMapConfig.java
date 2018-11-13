package team.vicilization.gamemap;

import java.util.HashMap;

class GameMapConfig {
    public static final HashMap<TerrainType, Integer> TERRAIN_MOBILITY_COST = new HashMap<TerrainType, Integer>(){
        {
            put(TerrainType.PLAIN, 1);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_DEFENCE_BUFF = new HashMap<TerrainType, Integer>(){
        {
            put(TerrainType.PLAIN, 1);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_PRODUCTIVITY = new HashMap<TerrainType, Integer>(){
        {
            put(TerrainType.PLAIN, 1);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_MONEY = new HashMap<TerrainType, Integer>(){
        {
            put(TerrainType.PLAIN, 1);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_FOOD = new HashMap<TerrainType, Integer>(){
        {
            put(TerrainType.PLAIN, 1);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_SCIENCE = new HashMap<TerrainType, Integer>(){
        {
            put(TerrainType.PLAIN, 1);
        }
    };

    public static final HashMap<LandformType, Integer> LANDFORM_MOBILITY_COST = new HashMap<LandformType, Integer>(){
        {
            put(LandformType.GRASSLANDS, 1);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_DEFENCE_BUFF = new HashMap<LandformType, Integer>(){
        {
            put(LandformType.GRASSLANDS, 1);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_PRODUCTIVITY = new HashMap<LandformType, Integer>(){
        {
            put(LandformType.GRASSLANDS, 1);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_MONEY = new HashMap<LandformType, Integer>(){
        {
            put(LandformType.GRASSLANDS, 1);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_FOOD = new HashMap<LandformType, Integer>(){
        {
            put(LandformType.GRASSLANDS, 1);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_SCIENCE = new HashMap<LandformType, Integer>(){
        {
            put(LandformType.GRASSLANDS, 1);
        }
    };

    public static final HashMap<ResourceType, Integer> RESOURCE_MOBILITY_COST = new HashMap<ResourceType, Integer>(){
        {
            put(ResourceType.NONE, 1);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_DEFENCE_BUFF = new HashMap<ResourceType, Integer>(){
        {
            put(ResourceType.NONE, 1);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_PRODUCTIVITY = new HashMap<ResourceType, Integer>(){
        {
            put(ResourceType.NONE, 1);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_MONEY = new HashMap<ResourceType, Integer>(){
        {
            put(ResourceType.NONE, 1);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_FOOD = new HashMap<ResourceType, Integer>(){
        {
            put(ResourceType.NONE, 1);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_SCIENCE = new HashMap<ResourceType, Integer>(){
        {
            put(ResourceType.NONE, 1);
        }
    };
}
