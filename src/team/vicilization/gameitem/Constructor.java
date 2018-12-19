package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;
import team.vicilization.gamemap.ResourceType;
      
public class Constructor extends Folk {
    public Constructor(Position position, Country country) {
        super(position, country);
        setSubType(UnitSubType.CONSTRUCTOR);
        this.country=country;
        this.position=position;
        this.health=100;
        this.times=3;
        this.unitInfo.attack=0;
        this.unitInfo.defence=0;
        this.unitInfo.mobility=3;
        this.unitInfo.productivityCost=3;
        this.unitInfo.moneyCost=100;
        this.unitInfo.requiredResource= ResourceType.NONE;
        //this.unitInfo.requiredScience;
    }
  
    public int getTimes() {
        return times;
    }
}
