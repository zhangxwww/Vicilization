package team.vicilization.gameitem;

import team.vicilization.mechanics.ScienceName;

public class BuildingInfo extends ProducableInfo{
    //========================Attributes======================//
    private ScienceName requiredScience;
    //========================establish=============================//
    public BuildingInfo(BuildingType buildingType){
        this.setRequiredScience(GameItemConfig.BUILDING_REQUIRED_SCIENCE.get(buildingType));
        super.setMoneyCost(GameItemConfig.BUILDING_MONEY_COST.get(buildingType));
        super.setProductivityCost(GameItemConfig.BUILDING_PRODUCTIVITY_COST.get(buildingType));
    }
    //========================Get-Set Methods======================//

    public ScienceName getRequiredScience() {
        return requiredScience;
    }

    public void setRequiredScience(ScienceName requiredScience) {
        this.requiredScience = requiredScience;
    }
}
