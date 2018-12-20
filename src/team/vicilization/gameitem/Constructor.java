package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;
import team.vicilization.gamemap.ResourceType;

public class Constructor extends Folk {
    public Constructor(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.CONSTRUCTOR);
        this.country = country;
        this.position = position;
        this.health = 100;
        this.times = 3;
        this.unitInfo.setAttack(GameItemConfig.UNIT_ATTACK.get(UnitSubType.CONSTRUCTOR));
        this.unitInfo.setDefence(GameItemConfig.UNIT_DEFENCE.get(UnitSubType.CONSTRUCTOR));
        this.unitInfo.setMobility(GameItemConfig.UNIT_MOBILITY.get(UnitSubType.CONSTRUCTOR));
        this.unitInfo.setProductivityCost(GameItemConfig.UNIT_PROCTIVITY_COST.get(UnitSubType.CONSTRUCTOR));
        this.unitInfo.setMoneyCost(GameItemConfig.UNIT_MONEY_COST.get(UnitSubType.CONSTRUCTOR));
        this.unitInfo.setRequiredResource(GameItemConfig.UNIT_REQUIRED_RESOURCE.get(UnitSubType.CONSTRUCTOR));
        this.unitInfo.setRequiredScience(GameItemConfig.UNIT_REQUIRED_SCIENCE.get(UnitSubType.CONSTRUCTOR));
    }

    public int getTimes() {
        return times;
    }
}
