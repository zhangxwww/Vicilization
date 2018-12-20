package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;

public class Spearman extends Fighter {
    public Spearman(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.SPEARMAN);
        this.health=100;
        this.unitInfo.setAttack                     (GameItemConfig.UNIT_ATTACK.get(UnitSubType.SPEARMAN));
        this.unitInfo.setDefence                   (GameItemConfig.UNIT_DEFENCE.get(UnitSubType.SPEARMAN));
        this.unitInfo.setMobility                 (GameItemConfig.UNIT_MOBILITY.get(UnitSubType.SPEARMAN));
        this.unitInfo.setProductivityCost  (GameItemConfig.UNIT_PROCTIVITY_COST.get(UnitSubType.SPEARMAN));
        this.unitInfo.setMoneyCost              (GameItemConfig.UNIT_MONEY_COST.get(UnitSubType.SPEARMAN));
        this.unitInfo.setRequiredResource(GameItemConfig.UNIT_REQUIRED_RESOURCE.get(UnitSubType.SPEARMAN));
        this.unitInfo.setRequiredScience  (GameItemConfig.UNIT_REQUIRED_SCIENCE.get(UnitSubType.SPEARMAN));
    }
}
