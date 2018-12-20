package team.vicilization.gameitem;

import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.LandSquare;

import java.util.Vector;

public class TestUnit {
    public static void main(String[] args){
        GameMap testMap=new GameMap();
        Unit testUnit=new Unit();
        Vector<LandSquare> testAvailable=testUnit.getAvailableLocation(testMap);
        for (LandSquare landSquare : testAvailable) {
            System.out.println("x: " + landSquare.getPosition().getX()+", y: " + landSquare.getPosition().getY());
        }
    }
}
