package team.vicilization.gameitem;

public interface Fightable {
    int getAttack();
    int getDefence();
    int getHealth();
    void injure();
    void die();
}
