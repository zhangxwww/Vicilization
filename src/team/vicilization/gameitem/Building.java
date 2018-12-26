package team.vicilization.gameitem;


import team.vicilization.country.Country;
import team.vicilization.util.Position;
import team.vicilization.util.Property;

public class Building {
    private BuildingType type;
    private Property buildingFlowValue;
    private BuildingInfo buildingInfo;

    public Building(BuildingType type){
        this.type=type;

        buildingFlowValue.setProductivity(GameItemConfig.BUILDING_FLOW_PRODUCTIVITY.get(type));
        buildingFlowValue.setMoney(GameItemConfig.BUILDING_FLOW_MONEY.get(type));
        buildingFlowValue.setFood(GameItemConfig.BUILDING_FLOW_FOOD.get(type));
        buildingFlowValue.setScience(GameItemConfig.BUILDING_FLOW_SCIENCE.get(type));
        buildingFlowValue.setScientistValue(GameItemConfig.BUILDING_FLOW_SCIENTIST_VALUE.get(type));
        buildingFlowValue.setTraderValue(GameItemConfig.BUILDING_FLOW_TRADER_VALUE.get(type));
        buildingFlowValue.setEngineerValue(GameItemConfig.BUILDING_FLOW_ENGINEER_VALUE.get(type));

        buildingInfo=new BuildingInfo(type);
    }



}
