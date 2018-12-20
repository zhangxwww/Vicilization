package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.mechanics.ScienceName;
import team.vicilization.util.Position;

public class Explorer extends Folk {

    public Explorer(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.EXPLORER);
        this.health = 100;
        this.unitInfo.setAttack(GameItemConfig.UNIT_ATTACK.get(UnitSubType.EXPLORER));
        this.unitInfo.setDefence(GameItemConfig.UNIT_DEFENCE.get(UnitSubType.EXPLORER));
        this.unitInfo.setMobility(GameItemConfig.UNIT_MOBILITY.get(UnitSubType.EXPLORER));
        this.unitInfo.setProductivityCost(GameItemConfig.UNIT_PROCTIVITY_COST.get(UnitSubType.EXPLORER));
        this.unitInfo.setMoneyCost(GameItemConfig.UNIT_MONEY_COST.get(UnitSubType.EXPLORER));
        this.unitInfo.setRequiredResource(GameItemConfig.UNIT_REQUIRED_RESOURCE.get(UnitSubType.EXPLORER));
        this.unitInfo.setRequiredScience(GameItemConfig.UNIT_REQUIRED_SCIENCE.get(UnitSubType.EXPLORER));
    }
}
