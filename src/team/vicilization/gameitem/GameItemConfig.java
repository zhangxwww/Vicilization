package team.vicilization.gameitem;

import team.vicilization.gameitem.building.BuildingType;
import team.vicilization.gameitem.unit.UnitSubType;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.mechanics.science.ScienceName;

import java.util.HashMap;

public class GameItemConfig {
    public static final HashMap<BuildingType, Integer> BUILDING_PRODUCTIVITY_COST = new HashMap<BuildingType, Integer>() {
        {
            put(BuildingType.ACADEMY, 200);
            put(BuildingType.COMMERCIAL_CERTER, 400);
            put(BuildingType.INDUSTRIAL_PARK, 600);
        }
    };
    public static final HashMap<BuildingType, Integer> BUILDING_MONEY_COST = new HashMap<BuildingType, Integer>() {
        {
            put(BuildingType.ACADEMY, 180);
            put(BuildingType.COMMERCIAL_CERTER, 180);
            put(BuildingType.INDUSTRIAL_PARK, 180);
        }
    };
    public static final HashMap<BuildingType, ScienceName> BUILDING_REQUIRED_SCIENCE = new HashMap<BuildingType, ScienceName>() {
        {
            put(BuildingType.ACADEMY, ScienceName.GEOMETRY);
            put(BuildingType.COMMERCIAL_CERTER, ScienceName.CALCULUS);
            put(BuildingType.INDUSTRIAL_PARK, ScienceName.PROBABILITY);
        }
    };
    public static final HashMap<BuildingType, Integer> BUILDING_FLOW_PRODUCTIVITY = new HashMap<BuildingType, Integer>() {
        {
            put(BuildingType.ACADEMY, 0);
            put(BuildingType.COMMERCIAL_CERTER, 0);
            put(BuildingType.INDUSTRIAL_PARK, 10);
        }
    };
    public static final HashMap<BuildingType, Integer> BUILDING_FLOW_MONEY = new HashMap<BuildingType, Integer>() {
        {
            put(BuildingType.ACADEMY, 0);
            put(BuildingType.COMMERCIAL_CERTER, 10);
            put(BuildingType.INDUSTRIAL_PARK, 0);
        }
    };
    public static final HashMap<BuildingType, Integer> BUILDING_FLOW_FOOD = new HashMap<BuildingType, Integer>() {
        {
            put(BuildingType.ACADEMY, 0);
            put(BuildingType.COMMERCIAL_CERTER, 0);
            put(BuildingType.INDUSTRIAL_PARK, 0);
        }
    };
    public static final HashMap<BuildingType, Integer> BUILDING_FLOW_SCIENCE = new HashMap<BuildingType, Integer>() {
        {
            put(BuildingType.ACADEMY, 10);
            put(BuildingType.COMMERCIAL_CERTER, 0);
            put(BuildingType.INDUSTRIAL_PARK, 0);
        }
    };
    public static final HashMap<BuildingType, Integer> BUILDING_FLOW_SCIENTIST_VALUE = new HashMap<BuildingType, Integer>() {
        {
            put(BuildingType.ACADEMY, 1);
            put(BuildingType.COMMERCIAL_CERTER, 0);
            put(BuildingType.INDUSTRIAL_PARK, 0);
        }
    };
    public static final HashMap<BuildingType, Integer> BUILDING_FLOW_TRADER_VALUE = new HashMap<BuildingType, Integer>() {
        {
            put(BuildingType.ACADEMY, 0);
            put(BuildingType.COMMERCIAL_CERTER, 1);
            put(BuildingType.INDUSTRIAL_PARK, 0);
        }
    };
    public static final HashMap<BuildingType, Integer> BUILDING_FLOW_ENGINEER_VALUE = new HashMap<BuildingType, Integer>() {
        {
            put(BuildingType.ACADEMY, 0);
            put(BuildingType.COMMERCIAL_CERTER, 0);
            put(BuildingType.INDUSTRIAL_PARK, 1);
        }
    };

    public static final HashMap<UnitSubType, Integer> UNIT_HEALTH = new HashMap<UnitSubType, Integer>() {
        {
            put(UnitSubType.KNIGHT, 120);
            put(UnitSubType.EXPLORER, 100);
            put(UnitSubType.ARCHER, 100);
            put(UnitSubType.CONSTRUCTOR, 100);
            put(UnitSubType.FOOTMAN, 100);
            put(UnitSubType.SPEARMAN, 100);
            put(UnitSubType.SWORDSMAN, 150);
            put(UnitSubType.ASSASSIN, 100);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_ATTACK = new HashMap<UnitSubType, Integer>() {
        {
            put(UnitSubType.KNIGHT, 50);
            put(UnitSubType.EXPLORER, 0);
            put(UnitSubType.ARCHER, 30);
            put(UnitSubType.CONSTRUCTOR, 0);
            put(UnitSubType.FOOTMAN, 40);
            put(UnitSubType.SPEARMAN, 40);
            put(UnitSubType.SWORDSMAN, 50);
            put(UnitSubType.ASSASSIN, 40);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_DEFENCE = new HashMap<UnitSubType, Integer>() {
        {
            put(UnitSubType.KNIGHT, 30);
            put(UnitSubType.EXPLORER, 0);
            put(UnitSubType.ARCHER, 20);
            put(UnitSubType.CONSTRUCTOR, 0);
            put(UnitSubType.FOOTMAN, 30);
            put(UnitSubType.SPEARMAN, 30);
            put(UnitSubType.SWORDSMAN, 40);
            put(UnitSubType.ASSASSIN, 5);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_MOBILITY = new HashMap<UnitSubType, Integer>() {
        {
            put(UnitSubType.KNIGHT, 6);
            put(UnitSubType.EXPLORER, 4);
            put(UnitSubType.ARCHER, 4);
            put(UnitSubType.CONSTRUCTOR, 4);
            put(UnitSubType.FOOTMAN, 4);
            put(UnitSubType.SPEARMAN, 4);
            put(UnitSubType.SWORDSMAN, 4);
            put(UnitSubType.ASSASSIN, 5);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_PRODUCTIVITY_COST = new HashMap<UnitSubType, Integer>() {
        {
            put(UnitSubType.KNIGHT, 200);
            put(UnitSubType.EXPLORER, 400);
            put(UnitSubType.ARCHER, 100);
            put(UnitSubType.CONSTRUCTOR, 250);
            put(UnitSubType.FOOTMAN, 100);
            put(UnitSubType.SPEARMAN, 150);
            put(UnitSubType.SWORDSMAN, 200);
            put(UnitSubType.ASSASSIN, 150);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_MONEY_COST = new HashMap<UnitSubType, Integer>() {
        {
            put(UnitSubType.KNIGHT, 150);
            put(UnitSubType.EXPLORER, 200);
            put(UnitSubType.ARCHER, 120);
            put(UnitSubType.CONSTRUCTOR, 100);
            put(UnitSubType.FOOTMAN, 100);
            put(UnitSubType.SPEARMAN, 70);
            put(UnitSubType.SWORDSMAN, 130);
            put(UnitSubType.ASSASSIN, 110);
        }
    };
    public static final HashMap<UnitSubType, ScienceName> UNIT_REQUIRED_SCIENCE = new HashMap<UnitSubType, ScienceName>() {
        {
            put(UnitSubType.KNIGHT, ScienceName.CALCULUS);
            put(UnitSubType.EXPLORER, ScienceName.NONE);
            put(UnitSubType.ARCHER, ScienceName.GEOMETRY);
            put(UnitSubType.CONSTRUCTOR, ScienceName.NONE);
            put(UnitSubType.FOOTMAN, ScienceName.NONE);
            put(UnitSubType.SPEARMAN, ScienceName.ARITHMETIC);
            put(UnitSubType.SWORDSMAN, ScienceName.STATISTICS);
            put(UnitSubType.ASSASSIN, ScienceName.ALGEBRA);
        }
    };
    public static final HashMap<UnitSubType, ResourceType> UNIT_REQUIRED_RESOURCE = new HashMap<UnitSubType, ResourceType>() {
        {
            put(UnitSubType.KNIGHT, ResourceType.NONE);
            put(UnitSubType.EXPLORER, ResourceType.NONE);
            put(UnitSubType.ARCHER, ResourceType.NONE);
            put(UnitSubType.CONSTRUCTOR, ResourceType.NONE);
            put(UnitSubType.FOOTMAN, ResourceType.NONE);
            put(UnitSubType.SPEARMAN, ResourceType.NONE);
            put(UnitSubType.SWORDSMAN, ResourceType.NONE);
            put(UnitSubType.ASSASSIN, ResourceType.NONE);
        }
    };

    public static final HashMap<UnitSubType, Integer> UNIT_RECOVERY = new HashMap<UnitSubType, Integer>() {
        {
            put(UnitSubType.EXPLORER, 8);
            put(UnitSubType.CONSTRUCTOR, 8);
            put(UnitSubType.KNIGHT, 10);
            put(UnitSubType.ARCHER, 10);
            put(UnitSubType.FOOTMAN, 10);
            put(UnitSubType.SPEARMAN, 10);
            put(UnitSubType.SWORDSMAN, 15);
            put(UnitSubType.ASSASSIN, 50);
        }
    };

    public static final HashMap<UnitSubType, ScienceName> UPDATE_NEED_SCIENCE = new HashMap<UnitSubType, ScienceName>() {
        {
            put(UnitSubType.FOOTMAN, ScienceName.STATISTICS);
        }
    };

    private GameItemConfig() {
    }
}
