package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.country.CountryName;
import team.vicilization.gameitem.city.City;
import team.vicilization.gameitem.city.CityName;
import team.vicilization.gameitem.unit.Constructor;
import team.vicilization.gameitem.unit.Footman;
import team.vicilization.gameitem.unit.UnitSubType;
import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import java.util.Vector;

public class TestUnit {
    public static void main(String[] args){
        GameMap testMap=new GameMap();
        Country C=new Country(CountryName.AMERICA);
        Position position=new Position();
        position.setX(1);
        position.setY(1);
        //战士单位
        Footman F=new Footman(position,C);
        //单位移动
        Vector<LandSquare> Reachable=F.getAvailableLocation(testMap);
        for (LandSquare i:Reachable){
            System.out.println("X = "+i.getPosition().getX()+", Y = "+i.getPosition().getY());
        }
        F.moveTo(Reachable.firstElement().getPosition());
        System.out.println("Current Position: X = "+F.getPosition().getX()+", Y = "+F.getPosition().getY());
        //单位受伤
        System.out.println("Initial Health: "+F.getHealth());
        F.injure(50);
        System.out.println("Health After Injury: "+F.getHealth());
        F.unitEndOfTurn();
        System.out.println("Health Next Turn: "+F.getHealth());
        F.injure(90);
        System.out.println("Whether Unit Alive: "+C.getUnits().contains(F));
        //城市
        City city=new City(C,position, CityName.BOMBAY,Reachable);
        //建造单位
        city.produce(UnitSubType.ASSASSIN);
        //结算
    }
}
