package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;

public class Knight extends Fighter {

    public Knight(Country country, Position position){
        this.type=UnitType.FIGHTER;
        this.subType=UnitSubType.KNIGHT;
        this.country=country;
        this.position=position;
        this.health=100;
        this.unitInfo.attack=4;
        this.unitInfo.defence=3;
        this.unitInfo.mobility=5;
        this.unitInfo.productivityCost=5;
        this.unitInfo.moneyCost=150;
        this.unitInfo.requiredResource= ResourceType.NONE;
        //this.unitInfo.requiredScience;
    }
}
