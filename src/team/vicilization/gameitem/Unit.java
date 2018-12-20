package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.GameMapConfig;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import javax.swing.*;
import java.lang.annotation.Annotation;
import java.util.Vector;

public abstract class Unit implements Movable,Selectable,Producable,Affiliable{

    protected UnitType type;
    protected UnitSubType subType;
    protected Country country;
    protected Position position;
    protected int health;
    protected UnitInfo unitInfo;
    protected static int unitRecover;


    // TODO 这两个东西要在回合结束的时候重置
    protected boolean movedThisTurn;
    protected boolean attackedThisTurn;


    public Unit(Position position, Country country,UnitType unitType,UnitSubType unitSubType) {
        this.position = position;
        this.country = country;
        this.type= unitType;
        this.subType=unitSubType;
        this.unitInfo=new UnitInfo(unitSubType);
        this.health=GameItemConfig.UNIT_HEALTH.get(unitSubType);
        this.movedThisTurn = false;
        this.attackedThisTurn = false;
    }
    
    public void delete(){

    }
    public void recover(){
        int initHealth=GameItemConfig.UNIT_HEALTH.get(this.subType);
        if (health<initHealth-unitRecover){
            health+=unitRecover;
        }else {
            health=initHealth;
        }
    }


    //--------------------------------------Movable
    @Override
    public Position currentLocation() {
        return position;
    }
    @Override
    public int getMobility() {
        return unitInfo.getMobility();
    }
    @Override
    public Vector<LandSquare> getAvailableLocation(GameMap map) {
        class locationStack{
            LandSquare[] stackLandsquare = new LandSquare[100];
            int[] resiMobility = new int[100];
            int landPtr=0;
            int resiPtr=0;
            public void push(int a){
                resiMobility[resiPtr]=a;
                resiPtr++;
            }
            public void push(LandSquare land){
                stackLandsquare[landPtr]=land;
                landPtr++;
            }
            public LandSquare popLandsquare(){
                if (landPtr==0){
                    return null;
                }else {
                    landPtr--;
                    LandSquare landSqr = stackLandsquare[landPtr];
                    return landSqr;
                }
            }
            public int popResimobility(){
                if(resiPtr==0){
                    return -100;
                }else {
                    resiPtr--;
                    return resiMobility[resiPtr];
                }
            }


            public Vector<LandSquare> getStackAvailableLocation(){
                Vector<LandSquare> availableSquare=new Vector<LandSquare>();
                int Mobility=getMobility();
                Position currPosition=getPosition();
                push(map.getSquare(currPosition.getX(), currPosition.getY()));
                push(Mobility);


                while (true){
                    LandSquare A=popLandsquare();
                    int B=popResimobility();
                    if(B==-100){
                        break;
                    }else if(B>=0){
                        if(!availableSquare.contains(A)) {
                            availableSquare.add(A);
                        }
                        Position p=A.getPosition();

                        if (p.getX() + 1 < GameMapConfig.MAP_WIDTH) {
                            Position p1 = new Position(p.getX() + 1, p.getY() + 0);
                            LandSquare L1 = map.getSquare(p.getX() + 1, p.getY() + 0);
                            if (map.getSquare(p.getX() + 1, p.getY() + 0).getMobilityCost() <= B) {
                                push(L1);
                                push(B - map.getSquare(p.getX() + 1, p.getY() + 0).getMobilityCost());
                            }
                        }

                        if (p.getX() - 1 >= 0) {
                            Position p2 = new Position(p.getX() - 1, p.getY() + 0);
                            LandSquare L2 = map.getSquare(p.getX() - 1, p.getY() + 0);
                            if (map.getSquare(p.getX() - 1, p.getY() + 0).getMobilityCost() <= B) {
                                push(L2);
                                push(B - map.getSquare(p.getX() - 1, p.getY() + 0).getMobilityCost());
                            }
                        }

                        if (p.getY() + 1 < GameMapConfig.MAP_HEIGHT) {
                            Position p3 = new Position(p.getX() + 0, p.getY() + 1);
                            LandSquare L3 = map.getSquare(p.getX() + 0, p.getY() + 1);
                            if (map.getSquare(p.getX() + 0, p.getY() + 1).getMobilityCost() <= B) {
                                push(L3);
                                push(B - map.getSquare(p.getX() + 0, p.getY() + 1).getMobilityCost());
                            }
                        }

                        if (p.getY() - 1 >= 0) {
                            Position p4 = new Position(p.getX() + 0, p.getY() - 1);
                            LandSquare L4 = map.getSquare(p.getX() + 0, p.getY() - 1);
                            if (map.getSquare(p.getX() + 0, p.getY() - 1).getMobilityCost() <= B) {
                                push(L4);
                                push(B - map.getSquare(p.getX() + 0, p.getY() - 1).getMobilityCost());
                            }
                        }

                    }
                }
                availableSquare.remove(map.getSquare(currPosition.getX(), currPosition.getY()));
                return availableSquare;
            }
        }
        locationStack myStack=new locationStack();
        return myStack.getStackAvailableLocation();
        //待验证
    }
    @Override
    public void moveTo(Position pos) {
        this.setPosition(pos);
        movedThisTurn = true;
    }
    

//------------------------------------------Fightable


    public int getHealth() {
        return health;
    }

    @Override
    public int moneyCost() {
        return 0;
    }
    @Override
    public int productivityCost() {
        return 0;
    }



    @Override
    public boolean ableToSelect() {
        return false;
    }
    @Override
    public void setAbleToSelect(boolean able) {

    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public Country getCountry() {
        return country;
    }

    public UnitType getType() {
        return type;
    }

    public UnitSubType getSubType() {
        return subType;
    }

    public void setType(UnitType type) {
        this.type = type;
    }

    public void setSubType(UnitSubType subType) {
        this.subType = subType;
    }

    public Position getPosition() {
        return position;
    }

    public UnitInfo getUnitInfo() {
        return unitInfo;
    }

    public boolean isAttackedThisTurn() {
        return attackedThisTurn;
    }

    public boolean isMovedThisTurn() {
        return movedThisTurn;
    }
}



