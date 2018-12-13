package team.vicilization.gameitem;

import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import java.util.Vector;

public interface Movable {
    public Position currentLocation();

    public int getMobility();

    public Vector<LandSquare> getAvailableLocation();

    public void moveTo(Position pos);
}
