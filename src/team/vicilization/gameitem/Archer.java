package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;

public class Archer extends Fighter {

    public Archer(Country country,Position position){
        this.type=UnitType.FIGHTER;
        this.subType=UnitSubType.ARCHER;
        this.country=country;
        this.position=position;
        this.health=100;
        this.unitInfo.attack=5;
        this.unitInfo.defence=2;
        this.unitInfo.mobility=4;
        this.unitInfo.productivityCost=3;
        this.unitInfo.moneyCost=120;
        this.unitInfo.requiredResource= ResourceType.NONE;
        //this.unitInfo.requiredScience;
    }
}
