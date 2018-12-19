package team.vicilization.gameitem;

import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import java.util.Vector;



public interface Movable {
    Position currentLocation();
    int getMobility();
    Vector<LandSquare> getAvailableLocation(GameMap map);
    void moveTo(Position pos);
}
