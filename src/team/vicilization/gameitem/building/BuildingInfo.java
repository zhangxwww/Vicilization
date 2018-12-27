package team.vicilization.gameitem.building;

import team.vicilization.gameitem.GameItemConfig;
import team.vicilization.gameitem.ProducableInfo;
import team.vicilization.mechanics.science.ScienceName;

public class BuildingInfo extends ProducableInfo {
    private ScienceName requiredScience;

    public BuildingInfo(BuildingType buildingType){
        this.setRequiredScience(GameItemConfig.BUILDING_REQUIRED_SCIENCE.get(buildingType));
        super.setMoneyCost(GameItemConfig.BUILDING_MONEY_COST.get(buildingType));
        super.setProductivityCost(GameItemConfig.BUILDING_PRODUCTIVITY_COST.get(buildingType));
    }

    public ScienceName getRequiredScience() {
        return requiredScience;
    }

    public void setRequiredScience(ScienceName requiredScience) {
        this.requiredScience = requiredScience;
    }
}
