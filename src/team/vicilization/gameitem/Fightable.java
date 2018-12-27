package team.vicilization.gameitem;

import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.LandSquare;

import java.util.Vector;

public interface Fightable {
    public int getAttack();

    public int getDefence();

    public int getHealth();

    public void injure(int damage);

    public void die();

    public boolean isDied();

    public Vector<LandSquare> getAttackRange(GameMap map);
}
