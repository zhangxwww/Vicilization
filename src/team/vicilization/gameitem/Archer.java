package team.vicilization.gameitem;

import team.vicilization.country.Country;

import team.vicilization.util.Position;
import team.vicilization.gamemap.ResourceType;

public class Archer extends Fighter {
    public Archer(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.ARCHER);
        this.health = 100;
        this.unitInfo.setAttack(GameItemConfig.UNIT_ATTACK.get(UnitSubType.ARCHER));
        this.unitInfo.setDefence(GameItemConfig.UNIT_DEFENCE.get(UnitSubType.ARCHER));
        this.unitInfo.setMobility(GameItemConfig.UNIT_MOBILITY.get(UnitSubType.ARCHER));
        this.unitInfo.setProductivityCost(GameItemConfig.UNIT_PROCTIVITY_COST.get(UnitSubType.ARCHER));
        this.unitInfo.setMoneyCost(GameItemConfig.UNIT_MONEY_COST.get(UnitSubType.ARCHER));
        this.unitInfo.setRequiredResource(GameItemConfig.UNIT_REQUIRED_RESOURCE.get(UnitSubType.ARCHER));
        this.unitInfo.setRequiredScience(GameItemConfig.UNIT_REQUIRED_SCIENCE.get(UnitSubType.ARCHER));

    }
}
