package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import javax.swing.*;
import java.util.Vector;

public class City extends JButton {
    CityName name;
    int population;
    Position location;
    Country country;
    CityFlowValue flowValue;
    CityStockValue stockValue;
    Vector<LandSquare> territory;
    Vector<BuildingType> constructedBuildings;
    Producable productingItem;
    int cityAttack;
    int cityDefence;
    int cityHealth;
    int recovery;
    Vector<BuildingType> allowedBuildings;
    Vector<Unit> allowedUnits;

    private void updateStock(){

    }
    private void recover(){

    }
    private  void produce(Producable production){

    }
    private void calculateFlowValue(){

    }
    private void addNewUnit(Unit unit){

    }
    private void addNewBuilding(BuildingType buildingType){

    }
    private boolean belongsTo(Country country){
        return false;
    }
    private boolean hasLandSquare(LandSquare landform){
        return false;
    }
}
