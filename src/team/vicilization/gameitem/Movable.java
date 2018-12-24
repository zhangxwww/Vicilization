package team.vicilization.gameitem;

import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import java.util.Vector;

public interface Movable {

    public Position currentLocation();

    public int getMobility();

    public void setMobility(int mobility);

    public Vector<LandSquare> getAvailableLocation(GameMap map);

    public void moveTo(Position pos);

}
