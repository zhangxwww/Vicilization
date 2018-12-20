package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;

public class Knight extends Fighter {
    public Knight(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.KNIGHT);
        this.health = 100;

        this.unitInfo.setAttack(GameItemConfig.UNIT_ATTACK.get(UnitSubType.KNIGHT));
        this.unitInfo.setDefence(GameItemConfig.UNIT_DEFENCE.get(UnitSubType.KNIGHT));
        this.unitInfo.setMobility(GameItemConfig.UNIT_MOBILITY.get(UnitSubType.KNIGHT));
        this.unitInfo.setProductivityCost(GameItemConfig.UNIT_PROCTIVITY_COST.get(UnitSubType.KNIGHT));
        this.unitInfo.setMoneyCost(GameItemConfig.UNIT_MONEY_COST.get(UnitSubType.KNIGHT));
        this.unitInfo.setRequiredResource(GameItemConfig.UNIT_REQUIRED_RESOURCE.get(UnitSubType.KNIGHT));
        this.unitInfo.setRequiredScience(GameItemConfig.UNIT_REQUIRED_SCIENCE.get(UnitSubType.KNIGHT));
    }
}
