package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;



public class Footman extends Fighter {
    public Footman(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.FOOTMAN);
        this.health=100;
        this.unitInfo.attack=3;
        this.unitInfo.defence=4;
        this.unitInfo.mobility=4;
        this.unitInfo.productivityCost=3;
        this.unitInfo.moneyCost=100;
        this.unitInfo.requiredResource= ResourceType.NONE;
        //this.unitInfo.requiredScience;
    }
}
