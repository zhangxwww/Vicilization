package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import javax.swing.*;
import java.lang.annotation.Annotation;
import java.util.Vector;

public class Unit extends JButton implements Movable,Selectable,Producable,Affiliable{
    UnitType type;
    UnitSubType subType;
    Country country;
    Position position;
    int health;
    UnitInfo unitInfo=new UnitInfo();


    boolean movedThisTurn;
    boolean attackedThisTurn;

    public void delete(){
        setVisible(false);
        setEnabled(false);
    }


    class availablePositionInfo{
        int lowestMobilityCost;
        //boolean available;
        public availablePositionInfo(int cost){
            lowestMobilityCost=cost;
        }
    }
    @Override
    public Position currentLocation() {
        return position;
    }
    @Override
    public int getMobility() {
        return unitInfo.mobility;
    }
    @Override
    public Vector<LandSquare> getAvailableLocation() {
      /*  Position currentLocation=currentLocation();
        int Mobility=getMobility();
        Vector<Position> availableLocation=new Vector<Position>();
        Vector<availablePositionInfo> availablePositionInfos=new Vector<availablePositionInfo>();
        //Vector<Position> adjacentLocations=new Vector<Position>();
        availableLocation.add(currentLocation());
        availablePositionInfos.add(new availablePositionInfo(0));
        int cost=0;
        while (cost<Mobility+1){
            for (Position position:availableLocation){
                Vector<Position> adjacentLocations=new Vector<Position>();
                Position p1=new Position(position.getX()+1,position.getY()+1);
                Position p2=new Position(position.getX()+1,position.getY()-1);
                Position p3=new Position(position.getX()-1,position.getY()+1);
                Position p4=new Position(position.getX()-1,position.getY()-1);

            }
        }*/


        //将可能到达的加入vector
        //逐层赋值
        //判断
        return null;
    }
    @Override
    public void moveTo(Position pos) {

    }



    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
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

}
