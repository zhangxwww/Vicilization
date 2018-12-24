package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.util.Position;

public abstract class Fighter extends Unit implements Fightable{


    public Fighter(Position position, Country country,UnitSubType unitSubType) {
        super(position, country,UnitType.FIGHTER,unitSubType);
        setType(UnitType.FIGHTER);
    }
//---------------------------Fightable
    @Override
    public int getAttack() {
        return super.getAttack();
    }

    @Override
    public int getDefence() {
        return super.getDefence();
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public void injure(int damage) {
        if (damage>=getHealth()){
            this.setHealth(0);
            this.die();
        }else {
            this.setHealth(this.getHealth()-damage);
        }
    }

    @Override
    public void die(){
        this.country.deleteUnit(this);
    }
}
