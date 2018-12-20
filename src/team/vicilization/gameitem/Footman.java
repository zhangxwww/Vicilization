package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;


public class Footman extends Fighter {
    public Footman(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.FOOTMAN);
        this.health = 100;
        this.unitInfo.setAttack(GameItemConfig.UNIT_ATTACK.get(UnitSubType.FOOTMAN));
        this.unitInfo.setDefence(GameItemConfig.UNIT_DEFENCE.get(UnitSubType.FOOTMAN));
        this.unitInfo.setMobility(GameItemConfig.UNIT_MOBILITY.get(UnitSubType.FOOTMAN));
        this.unitInfo.setProductivityCost(GameItemConfig.UNIT_PROCTIVITY_COST.get(UnitSubType.FOOTMAN));
        this.unitInfo.setMoneyCost(GameItemConfig.UNIT_MONEY_COST.get(UnitSubType.FOOTMAN));
        this.unitInfo.setRequiredResource(GameItemConfig.UNIT_REQUIRED_RESOURCE.get(UnitSubType.FOOTMAN));
        this.unitInfo.setRequiredScience(GameItemConfig.UNIT_REQUIRED_SCIENCE.get(UnitSubType.FOOTMAN));
    }
}
