package team.vicilization.gameitem;

import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import java.util.Vector;

public interface Movable {
    Position currentLocation();
    int getMobility();
    Vector<LandSquare> getAvailableLocation();
    void moveTo(Position pos);
}
