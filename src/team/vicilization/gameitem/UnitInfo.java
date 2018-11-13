package team.vicilization.gameitem;

import team.vicilization.mechanics.ScienceName;
import team.vicilization.gamemap.*;

public class UnitInfo {
    //========================Attributes======================//
    int attack;
    int defence;
    int mobility;
    int productivityCost;
    int moneyCost;

    ScienceName requiredScience;
    ResourceType requiredResource;

    //========================Get-Set Methods======================//

    public int getAttack() {
        return attack;
    }
    public int getDefence() {
        return defence;
    }
    public int getMobility() {
        return mobility;
    }
    public int getMoneyCost() {
        return moneyCost;
    }
    public int getProductivityCost() {
        return productivityCost;
    }

    public ResourceType getRequiredResource() {
        return requiredResource;
    }
    public ScienceName getRequiredScience() {
        return requiredScience;
    }
}
