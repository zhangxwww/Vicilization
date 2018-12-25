package team.vicilization.gamemap;

import team.vicilization.util.Property;

import java.util.HashMap;
import java.util.Vector;


public class GameMapConfig {

    public static final int MAP_WIDTH = 40;
    public static final int MAP_HEIGHT = 30;

    public static final int TEMPERATURE = 25;
    public static final int MOISTRURE = 20; //Must be > 0

    public static final double MOISTURE_BOUND = 15;
    public static final double TEMPERATURE_BOUND_COLD = 3;
    public static final double TEMPERATURE_BOUND_HOT = 16;

    public static final int MOUNTAIN_NUM = 8;
    public static final int MOUNTAIN_SERIAL = 8;
    public static final int[][][] RIDGE_XY = {
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}, {2, 2}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            {{0, 0}, {1, 0}},
            {{0, 0}, {-1, 0}, {-2, 0}},
            {{0, 0}, {-1, 0}, {-1, -1}, {1, 0}, {1, 1}, {1,2}},
            {{0, 0}, {0, -1}, {1, -1}},
            {{0, 0}, {0, -1}, {1, -1}, {1, 0}, {-1, 0}, {-2, 0}, {-2, -1}, {-2, -2}, {-2, -3}},
            {{0, 0}, {0, 1}, {-1, 1}}
    };

    public static final int RIVER_NUM = 6;
    public static final int RIVER_SERIAL = 4;
    public static final int[][][] RIVER_XY = {
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {3, 1}, {4, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {-1, 2}},
            {{0, 0}, {0, 1}, {1, 1}, {2, 1}, {2, 0}, {2, -1}, {2, -2}, {3, -2}, {4, -2}, {5, -2}, {5, -3}, {6, -3}, {7, -3}, {8, -3}, {9, -3}, {9, -2}},
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {4, 1}, {4, 2}, {3, 2}}
    };
    public static final TerrainType[][] RIVER_KIND = {
            {TerrainType.RIVER_ROW,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_SW,
                    TerrainType.RIVER_NE,
                    TerrainType.LAKE},
            {TerrainType.RIVER_COL,
                    TerrainType.RIVER_COL,
                    TerrainType.RIVER_NW,
                    TerrainType.LAKE},
            {TerrainType.RIVER_COL,
                    TerrainType.RIVER_NE,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_NW,
                    TerrainType.RIVER_COL,
                    TerrainType.RIVER_COL,
                    TerrainType.RIVER_SE,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_NW,
                    TerrainType.RIVER_SE,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_SW,
                    TerrainType.LAKE},
            {TerrainType.RIVER_ROW,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_ROW,
                    TerrainType.RIVER_SW,
                    TerrainType.RIVER_COL,
                    TerrainType.RIVER_NW,
                    TerrainType.LAKE},
    };

    public static final double RAND_LEVEL0 = 0.50;
    public static final double RAND_LEVEL1 = 0.75;
    public static final double RAND_LEVEL2 = 0.98;
    public static final double RAND_LEVEL3 = 0.85;

    public static final HashMap<TerrainType, Integer> TERRAIN_MOBILITY_COST = new HashMap<TerrainType, Integer>() {
        {
            put(TerrainType.PLAIN, 1);
            put(TerrainType.HILL, 2);
            put(TerrainType.RIDGE, 1000);
            put(TerrainType.LAKE, 3);
            put(TerrainType.RIVER_ROW, 3);
            put(TerrainType.RIVER_COL, 3);
            put(TerrainType.RIVER_NE, 3);
            put(TerrainType.RIVER_NW, 3);
            put(TerrainType.RIVER_SE, 3);
            put(TerrainType.RIVER_SW, 3);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_DEFENCE_BUFF = new HashMap<TerrainType, Integer>() {
        {
            put(TerrainType.PLAIN, 1);
            put(TerrainType.HILL, 2);
            put(TerrainType.RIDGE, 0);
            put(TerrainType.LAKE, 0);
            put(TerrainType.RIVER_ROW, 0);
            put(TerrainType.RIVER_COL, 0);
            put(TerrainType.RIVER_NE, 0);
            put(TerrainType.RIVER_NW, 0);
            put(TerrainType.RIVER_SE, 0);
            put(TerrainType.RIVER_SW, 0);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_PRODUCTIVITY = new HashMap<TerrainType, Integer>() {
        {
            put(TerrainType.PLAIN, 1);
            put(TerrainType.HILL, 2);
            put(TerrainType.RIDGE, 3);
            put(TerrainType.LAKE, 0);
            put(TerrainType.RIVER_ROW, 1);
            put(TerrainType.RIVER_COL, 1);
            put(TerrainType.RIVER_NE, 1);
            put(TerrainType.RIVER_NW, 1);
            put(TerrainType.RIVER_SE, 1);
            put(TerrainType.RIVER_SW, 1);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_MONEY = new HashMap<TerrainType, Integer>() {
        {
            put(TerrainType.PLAIN, 0);
            put(TerrainType.HILL, 0);
            put(TerrainType.RIDGE, 0);
            put(TerrainType.LAKE, 0);
            put(TerrainType.RIVER_ROW, 0);
            put(TerrainType.RIVER_COL, 0);
            put(TerrainType.RIVER_NE, 0);
            put(TerrainType.RIVER_NW, 0);
            put(TerrainType.RIVER_SE, 0);
            put(TerrainType.RIVER_SW, 0);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_FOOD = new HashMap<TerrainType, Integer>() {
        {
            put(TerrainType.PLAIN, 2);
            put(TerrainType.HILL, 1);
            put(TerrainType.RIDGE, 0);
            put(TerrainType.LAKE, 1);
            put(TerrainType.RIVER_ROW, 1);
            put(TerrainType.RIVER_COL, 1);
            put(TerrainType.RIVER_NE, 1);
            put(TerrainType.RIVER_NW, 1);
            put(TerrainType.RIVER_SE, 1);
            put(TerrainType.RIVER_SW, 1);
        }
    };
    public static final HashMap<TerrainType, Integer> TERRAIN_SCIENCE = new HashMap<TerrainType, Integer>() {
        {
            put(TerrainType.PLAIN, 0);
            put(TerrainType.HILL, 0);
            put(TerrainType.RIDGE, 1);
            put(TerrainType.LAKE, 0);
            put(TerrainType.RIVER_ROW, 0);
            put(TerrainType.RIVER_COL, 0);
            put(TerrainType.RIVER_NE, 0);
            put(TerrainType.RIVER_NW, 0);
            put(TerrainType.RIVER_SE, 0);
            put(TerrainType.RIVER_SW, 0);
        }
    };

    public static final HashMap<LandformType, Integer> LANDFORM_MOBILITY_COST = new HashMap<LandformType, Integer>() {
        {
            put(LandformType.NONE, 0);
            put(LandformType.GRASSLANDS, 0);
            put(LandformType.FOREST, 1);
            put(LandformType.RAINFOREST, 1);
            put(LandformType.FROZENGROUND, 0);
            put(LandformType.DESERT, 0);
            put(LandformType.MARSH, 1);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_DEFENCE_BUFF = new HashMap<LandformType, Integer>() {
        {
            put(LandformType.NONE, 0);
            put(LandformType.GRASSLANDS, 1);
            put(LandformType.FOREST, 2);
            put(LandformType.RAINFOREST, 2);
            put(LandformType.FROZENGROUND, 1);
            put(LandformType.DESERT, 1);
            put(LandformType.MARSH, 0);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_PRODUCTIVITY = new HashMap<LandformType, Integer>() {
        {
            put(LandformType.NONE, 0);
            put(LandformType.GRASSLANDS, 1);
            put(LandformType.FOREST, 2);
            put(LandformType.RAINFOREST, 2);
            put(LandformType.FROZENGROUND, 0);
            put(LandformType.DESERT, 1);
            put(LandformType.MARSH, 0);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_MONEY = new HashMap<LandformType, Integer>() {
        {
            put(LandformType.NONE, 0);
            put(LandformType.GRASSLANDS, 0);
            put(LandformType.FOREST, 0);
            put(LandformType.RAINFOREST, 1);
            put(LandformType.FROZENGROUND, 0);
            put(LandformType.DESERT, 0);
            put(LandformType.MARSH, 0);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_FOOD = new HashMap<LandformType, Integer>() {
        {
            put(LandformType.NONE, 0);
            put(LandformType.GRASSLANDS, 1);
            put(LandformType.FOREST, 1);
            put(LandformType.RAINFOREST, 2);
            put(LandformType.FROZENGROUND, 0);
            put(LandformType.DESERT, 0);
            put(LandformType.MARSH, 2);
        }
    };
    public static final HashMap<LandformType, Integer> LANDFORM_SCIENCE = new HashMap<LandformType, Integer>() {
        {
            put(LandformType.NONE, 0);
            put(LandformType.GRASSLANDS, 0);
            put(LandformType.FOREST, 0);
            put(LandformType.RAINFOREST, 1);
            put(LandformType.FROZENGROUND, 0);
            put(LandformType.DESERT, 0);
            put(LandformType.MARSH, 0);
        }
    };

    public static final HashMap<LandformType, Property> LANDFORM_HARVEST = new HashMap<LandformType, Property>() {
        {
            put(LandformType.FOREST,       new Property(8,0,0,0,0,0,0));
            put(LandformType.RAINFOREST,   new Property(5,0,5,0,0,0,0));
            put(LandformType.MARSH,        new Property(0,0,8,0,0,0,0));
            // put(LandformType.NONE,         new Property(0,0,0,0,0,0,0));
            // put(LandformType.GRASSLANDS,   new Property(0,0,0,0,0,0,0));
            // put(LandformType.FROZENGROUND, new Property(0,0,0,0,0,0,0));
            // put(LandformType.DESERT,       new Property(0,0,0,0,0,0,0));
        }
    };

    public static final HashMap<ResourceType, Integer> RESOURCE_MOBILITY_COST = new HashMap<ResourceType, Integer>() {
        {
            put(ResourceType.NONE, 0);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_DEFENCE_BUFF = new HashMap<ResourceType, Integer>() {
        {
            put(ResourceType.NONE, 0);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_PRODUCTIVITY = new HashMap<ResourceType, Integer>() {
        {
            put(ResourceType.NONE, 0);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_MONEY = new HashMap<ResourceType, Integer>() {
        {
            put(ResourceType.NONE, 0);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_FOOD = new HashMap<ResourceType, Integer>() {
        {
            put(ResourceType.NONE, 0);
        }
    };
    public static final HashMap<ResourceType, Integer> RESOURCE_SCIENCE = new HashMap<ResourceType, Integer>() {
        {
            put(ResourceType.NONE, 0);

        }
    };

    private GameMapConfig() {
    }
}
