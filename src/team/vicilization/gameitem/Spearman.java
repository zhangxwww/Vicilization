package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;

public class Spearman extends Fighter {

    public Spearman(Country country, Position position){
        this.type=UnitType.FIGHTER;
        this.subType=UnitSubType.SPEARMAN;
        this.country=country;
        this.position=position;
        this.health=100;
        this.unitInfo.attack=5;
        this.unitInfo.defence=3;
        this.unitInfo.mobility=3;
        this.unitInfo.productivityCost=2;
        this.unitInfo.moneyCost=70;
        this.unitInfo.requiredResource= ResourceType.STONE;
        //this.unitInfo.requiredScience;
    }
}
