package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.util.Position;

public class Constructor extends  Folk{
    int times;
    void reduceTimes(){
        this.times-=1;
    }

    public Constructor(Country country, Position position){
        this.type=UnitType.FOLK;
        this.subType=UnitSubType.CONSTRUCTOR;
        this.country=country;
        this.position=position;
        this.health=100;
        this.unitInfo.attack=0;
        this.unitInfo.defence=0;
        this.unitInfo.mobility=3;
        this.unitInfo.productivityCost=3;
        this.unitInfo.moneyCost=100;
        this.unitInfo.requiredResource= ResourceType.NONE;
        //this.unitInfo.requiredScience;
    }
}
