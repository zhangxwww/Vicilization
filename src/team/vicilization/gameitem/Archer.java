package team.vicilization.gameitem;

import team.vicilization.country.Country;

import team.vicilization.util.Position;
import team.vicilization.gamemap.ResourceType;

public class Archer extends Fighter {
    public Archer(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.ARCHER);
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
