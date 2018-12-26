package team.vicilization.gameitem;

import team.vicilization.gamemap.ResourceType;
import team.vicilization.mechanics.ScienceName;

import java.util.HashMap;

public class GameItemConfig {
    public static final HashMap<BuildingType,Integer> BUILDING_PRODUCTIVITY_COST= new HashMap<BuildingType,Integer>(){
        {
            put(BuildingType.ACADEMY,6);
            put(BuildingType.COMMERCIAL_CERTER,6);
            put(BuildingType.INDUSTRIAL_PARK,6);
        }
    };
    public static final HashMap<BuildingType,Integer> BUILDING_MONEY_COST= new HashMap<BuildingType,Integer>(){
        {
            put(BuildingType.ACADEMY,180);
            put(BuildingType.COMMERCIAL_CERTER,180);
            put(BuildingType.INDUSTRIAL_PARK,180);
        }
    };
    public static final HashMap<BuildingType,ScienceName> BUILDING_REQUIRED_SCIENCE= new HashMap<BuildingType, ScienceName>(){
        {
            put(BuildingType.ACADEMY,ScienceName.ARITHMETIC);
            put(BuildingType.COMMERCIAL_CERTER,ScienceName.ARITHMETIC);
            put(BuildingType.INDUSTRIAL_PARK,ScienceName.ARITHMETIC);
        }
    };
    public static final HashMap<BuildingType,Integer> BUILDING_FLOW_PRODUCTIVITY=new HashMap<BuildingType,Integer>(){
        {
            put(BuildingType.ACADEMY,0);
            put(BuildingType.COMMERCIAL_CERTER,0);
            put(BuildingType.INDUSTRIAL_PARK,2);
        }
    };
    public static final HashMap<BuildingType,Integer> BUILDING_FLOW_MONEY=new HashMap<BuildingType,Integer>(){
        {
            put(BuildingType.ACADEMY,0);
            put(BuildingType.COMMERCIAL_CERTER,10);
            put(BuildingType.INDUSTRIAL_PARK,0);
        }
    };
    public static final HashMap<BuildingType,Integer> BUILDING_FLOW_FOOD=new HashMap<BuildingType,Integer>(){
        {
            put(BuildingType.ACADEMY,0);
            put(BuildingType.COMMERCIAL_CERTER,0);
            put(BuildingType.INDUSTRIAL_PARK,0);
        }
    };
    public static final HashMap<BuildingType,Integer> BUILDING_FLOW_SCIENCE=new HashMap<BuildingType,Integer>(){
        {
            put(BuildingType.ACADEMY,2);
            put(BuildingType.COMMERCIAL_CERTER,0);
            put(BuildingType.INDUSTRIAL_PARK,0);
        }
    };
    public static final HashMap<BuildingType,Integer> BUILDING_FLOW_SCIENTIST_VALUE=new HashMap<BuildingType,Integer>(){
        {
            put(BuildingType.ACADEMY,1);
            put(BuildingType.COMMERCIAL_CERTER,0);
            put(BuildingType.INDUSTRIAL_PARK,0);
        }
    };
    public static final HashMap<BuildingType,Integer> BUILDING_FLOW_TRADER_VALUE=new HashMap<BuildingType,Integer>(){
        {
            put(BuildingType.ACADEMY,0);
            put(BuildingType.COMMERCIAL_CERTER,1);
            put(BuildingType.INDUSTRIAL_PARK,0);
        }
    };
    public static final HashMap<BuildingType,Integer> BUILDING_FLOW_ENGINEER_VALUE=new HashMap<BuildingType,Integer>(){
        {
            put(BuildingType.ACADEMY,0);
            put(BuildingType.COMMERCIAL_CERTER,0);
            put(BuildingType.INDUSTRIAL_PARK,1);
        }
    };



    public static final HashMap<UnitSubType, Integer> UNIT_HEALTH = new HashMap<UnitSubType, Integer>(){
        {
            put(UnitSubType.KNIGHT, 100);
            put(UnitSubType.EXPLORER, 100);
            put(UnitSubType.ARCHER, 100);
            put(UnitSubType.CONSTRUCTOR, 100);
            put(UnitSubType.FOOTMAN, 100);
            put(UnitSubType.SPEARMAN, 100);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_ATTACK = new HashMap<UnitSubType, Integer>(){
        {
            put(UnitSubType.KNIGHT, 4);
            put(UnitSubType.EXPLORER, 0);
            put(UnitSubType.ARCHER, 5);
            put(UnitSubType.CONSTRUCTOR, 0);
            put(UnitSubType.FOOTMAN, 3);
            put(UnitSubType.SPEARMAN, 5);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_DEFENCE = new HashMap<UnitSubType, Integer>(){
        {
            put(UnitSubType.KNIGHT, 3);
            put(UnitSubType.EXPLORER, 0);
            put(UnitSubType.ARCHER, 2);
            put(UnitSubType.CONSTRUCTOR, 0);
            put(UnitSubType.FOOTMAN, 4);
            put(UnitSubType.SPEARMAN, 3);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_MOBILITY = new HashMap<UnitSubType, Integer>(){
        {
            put(UnitSubType.KNIGHT, 5);
            put(UnitSubType.EXPLORER, 3);
            put(UnitSubType.ARCHER, 4);
            put(UnitSubType.CONSTRUCTOR, 3);
            put(UnitSubType.FOOTMAN, 4);
            put(UnitSubType.SPEARMAN, 3);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_PRODUCTIVITY_COST = new HashMap<UnitSubType, Integer>(){
        {
            put(UnitSubType.KNIGHT, 5);
            put(UnitSubType.EXPLORER, 5);
            put(UnitSubType.ARCHER, 3);
            put(UnitSubType.CONSTRUCTOR, 3);
            put(UnitSubType.FOOTMAN, 3);
            put(UnitSubType.SPEARMAN, 2);
        }
    };
    public static final HashMap<UnitSubType, Integer> UNIT_MONEY_COST = new HashMap<UnitSubType, Integer>(){
        {
            put(UnitSubType.KNIGHT, 150);
            put(UnitSubType.EXPLORER, 200);
            put(UnitSubType.ARCHER, 120);
            put(UnitSubType.CONSTRUCTOR,100);
            put(UnitSubType.FOOTMAN, 100);
            put(UnitSubType.SPEARMAN, 70);
        }
    };
    public static final HashMap<UnitSubType, ScienceName> UNIT_REQUIRED_SCIENCE = new HashMap<UnitSubType, ScienceName>(){
        {
            put(UnitSubType.KNIGHT, ScienceName.ARITHMETIC);
            put(UnitSubType.EXPLORER, ScienceName.ARITHMETIC);
            put(UnitSubType.ARCHER, ScienceName.ARITHMETIC);
            put(UnitSubType.CONSTRUCTOR, ScienceName.ARITHMETIC);
            put(UnitSubType.FOOTMAN, ScienceName.ARITHMETIC);
            put(UnitSubType.SPEARMAN, ScienceName.ARITHMETIC);
        }
    };
    public static final HashMap<UnitSubType, ResourceType> UNIT_REQUIRED_RESOURCE = new HashMap<UnitSubType, ResourceType>(){
        {
            put(UnitSubType.KNIGHT, ResourceType.NONE);
            put(UnitSubType.EXPLORER, ResourceType.NONE);
            put(UnitSubType.ARCHER, ResourceType.NONE);
            put(UnitSubType.CONSTRUCTOR, ResourceType.NONE);
            put(UnitSubType.FOOTMAN, ResourceType.NONE);
            put(UnitSubType.SPEARMAN, ResourceType.NONE);
        }
    };

    private GameItemConfig(){}
}
