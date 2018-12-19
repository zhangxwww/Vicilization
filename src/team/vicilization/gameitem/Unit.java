package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import javax.swing.*;
import java.lang.annotation.Annotation;
import java.util.Vector;

public class Unit implements Movable,Selectable,Producable,Affiliable{
    UnitType type;
    UnitSubType subType;
    Country country;
    Position position;
    int health;
    UnitInfo unitInfo=new UnitInfo();


    boolean movedThisTurn;
    boolean attackedThisTurn;

    public void delete(){

    }


    //--------------------------------------Movable
    @Override
    public Position currentLocation() {
        return position;
    }
    @Override
    public int getMobility() {
        return unitInfo.mobility;
    }
    @Override
    public Vector<LandSquare> getAvailableLocation(GameMap map) {
        class locationStack{
            LandSquare[] stackLandsquare;
            int[] resiMobility;
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

                        Position p1=new Position(p.getX()+1,p.getY()+0);
                        LandSquare L1=map.getSquare(p.getX()+1,p.getY()+0);
                        if(map.getSquare(p.getX()+1,p.getY()+0).getMobilityCost()<=B){
                            push(L1);
                            push(B-map.getSquare(p.getX()+1,p.getY()+0).getMobilityCost());
                        }

                        Position p2=new Position(p.getX()-1,p.getY()+0);
                        LandSquare L2=map.getSquare(p.getX()-1,p.getY()+0);
                        if(map.getSquare(p.getX()-1,p.getY()+0).getMobilityCost()<=B){
                            push(L2);
                            push(B-map.getSquare(p.getX()-1,p.getY()+0).getMobilityCost());
                        }

                        Position p3=new Position(p.getX()+0,p.getY()+1);
                        LandSquare L3=map.getSquare(p.getX()+0,p.getY()+1);
                        if(map.getSquare(p.getX()+0,p.getY()+1).getMobilityCost()<=B){
                            push(L3);
                            push(B-map.getSquare(p.getX()+0,p.getY()+1).getMobilityCost());
                        }

                        Position p4=new Position(p.getX()+0,p.getY()-1);
                        LandSquare L4=map.getSquare(p.getX()+0,p.getY()-1);
                        if(map.getSquare(p.getX()+0,p.getY()-1).getMobilityCost()<=B){
                            push(L4);
                            push(B-map.getSquare(p.getX()+0,p.getY()-1).getMobilityCost());
                        }

                    }
                }
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
    }

//------------------------------------------Fightable





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

    public Unit() {

    }

    public Unit(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

}



