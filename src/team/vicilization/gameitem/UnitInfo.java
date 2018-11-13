package team.vicilization.gameitem;

public class UnitInfo {
    //========================Attributes======================//
    int attack;
    int defence;
    int mobility;
    int productivityCost;
    int moneyCost;

    ScienceName requiredScience;
    ResourceName requiresResource;

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

    public ResourceName getRequiresResource() {
        return requiresResource;
    }
    public ScienceName getRequiredScience() {
        return requiredScience;
    }
}
