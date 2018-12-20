package team.vicilization.gameitem;

import team.vicilization.mechanics.ScienceName;
import team.vicilization.gamemap.*;

public class UnitInfo {
    //========================Attributes======================//
    private int attack;
    private int defence;
    private int mobility;
    private int productivityCost;
    private int moneyCost;

    private ScienceName requiredScience;
    private ResourceType requiredResource;
    //========================establish=============================//
    public UnitInfo(UnitSubType unitSubType){
        this.attack=GameItemConfig.UNIT_ATTACK.get(unitSubType);
        this.defence=GameItemConfig.UNIT_DEFENCE.get(unitSubType);
        this.mobility=GameItemConfig.UNIT_MOBILITY.get(unitSubType);
        this.productivityCost=GameItemConfig.UNIT_PRODUCTIVITY_COST.get(unitSubType);
        this.moneyCost=GameItemConfig.UNIT_MONEY_COST.get(unitSubType);

        this.requiredScience=GameItemConfig.UNIT_REQUIRED_SCIENCE.get(unitSubType);
        this.requiredResource=GameItemConfig.UNIT_REQUIRED_RESOURCE.get(unitSubType);
    }

    //========================Get-Set Methods======================//

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMobility() {
        return mobility;
    }

    public void setMobility(int mobility) {
        this.mobility = mobility;
    }

    public int getMoneyCost() {
        return moneyCost;
    }

    public void setMoneyCost(int moneyCost) {
        this.moneyCost = moneyCost;
    }

    public int getProductivityCost() {
        return productivityCost;
    }

    public void setProductivityCost(int productivityCost) {
        this.productivityCost = productivityCost;
    }

    public ResourceType getRequiredResource() {
        return requiredResource;
    }

    public void setRequiredResource(ResourceType requiredResource) {
        this.requiredResource = requiredResource;
    }

    public ScienceName getRequiredScience() {
        return requiredScience;
    }

    public void setRequiredScience(ScienceName requiredScience) {
        this.requiredScience = requiredScience;
    }

}
