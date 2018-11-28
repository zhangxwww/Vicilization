package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;

public class Footman extends Fighter {


    public Footman(Country country, Position position){
        this.type=UnitType.FIGHTER;
        this.subType=UnitSubType.FOOTMAN;
        this.country=country;
        this.position=position;
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
