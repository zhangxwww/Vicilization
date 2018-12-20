package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;
import team.vicilization.gamemap.ResourceType;
      
public class Constructor extends Folk {
    private int times;

    public Constructor(Position position, Country country) {
        super(position, country,UnitSubType.SPEARMAN);
        setSubType(UnitSubType.CONSTRUCTOR);

    }
  
    public int getTimes() {
        return times;
    }
}
