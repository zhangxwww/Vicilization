package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.mechanics.ScienceName;
import team.vicilization.util.Position;

public class Explorer extends Folk{




    public Explorer(Country country, Position position){
        this.type=UnitType.FOLK;
        this.subType=UnitSubType.EXPLORER;
        this.country=country;
        this.position=position;
        this.health=100;
        this.unitInfo.attack=0;
        this.unitInfo.defence=0;
        this.unitInfo.mobility=3;
        this.unitInfo.productivityCost=5;
        this.unitInfo.moneyCost=200;
        this.unitInfo.requiredResource= ResourceType.NONE;
        //this.unitInfo.requiredScience;
    }

}
