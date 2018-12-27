package team.vicilization.gameitem.unit;

import team.vicilization.country.Country;
import team.vicilization.gameitem.GameItemConfig;
import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import java.util.Vector;

public abstract class Fighter extends Unit implements Fightable, Updateable {

    private boolean canUpdate;

    public boolean calculateCanUpdate(Country country) {
        if (!GameItemConfig.UPDATE_NEED_SCIENCE.containsKey(this.subType)) {
            this.canUpdate = false;
        } else {
            if (country.getLearntScience().contains(GameItemConfig.UPDATE_NEED_SCIENCE.get(this.subType))) {
                this.canUpdate = true;
            } else {
                this.canUpdate = false;
            }
        }
        return canUpdate;
    }

    public Fighter(Position position, Country country, UnitSubType unitSubType) {
        super(position, country, UnitType.FIGHTER, unitSubType);
        setType(UnitType.FIGHTER);
        calculateCanUpdate(country);
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

    public Vector<LandSquare> getAttackRange(GameMap map) {
        Position currentLocation = super.currentLocation();
        Vector<LandSquare> availableSquare = new Vector<LandSquare>();
        int attackRange = 0;
        if (this.subType == UnitSubType.ARCHER) {
            attackRange = 2;
        } else {
            attackRange = 1;
        }
        for (int j = 0; j < attackRange; j++) {
            for (int i = 0; i <= j; i++) {
                Position p1 = new Position(currentLocation.getX() + i, currentLocation.getY() + attackRange - i);
                Position p2 = new Position(currentLocation.getX() - i, currentLocation.getY() - attackRange + i);
                Position p3 = new Position(currentLocation.getX() + attackRange - i, currentLocation.getY() + i);
                Position p4 = new Position(currentLocation.getX() - attackRange + i, currentLocation.getY() - i);
                if (map.isLegalPosition(p1)) {
                    availableSquare.add(map.getSquare(p1));
                }
                if (map.isLegalPosition(p2)) {
                    availableSquare.add(map.getSquare(p2));
                }
                if (map.isLegalPosition(p3)) {
                    availableSquare.add(map.getSquare(p3));
                }
                if (map.isLegalPosition(p4)) {
                    availableSquare.add(map.getSquare(p4));
                }
            }
        }

        return availableSquare;
    }

    @Override
    public void injure(int damage) {
        if (damage >= getHealth()) {
            this.setHealth(0);
            this.die();
        } else {
            this.setHealth(this.getHealth() - damage);
        }
    }

    @Override
    public boolean isDied() {
        return this.getHealth() <= 0;
    }

    @Override
    public void die() {
        this.country.deleteUnit(this);
    }


    public boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    @Override
    public boolean isUpgradable() {
        if (!GameItemConfig.UPDATE_NEED_SCIENCE.containsKey(this.subType)) {
            return false;
        } else {
            if (country.getLearntScience().contains(GameItemConfig.UPDATE_NEED_SCIENCE.get(this.subType))) {
                return true;
            } else {
                return false;
            }
        }
    }
}
