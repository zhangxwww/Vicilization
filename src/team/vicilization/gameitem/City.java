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

    public City(Country country, Position position) {

    }

    public void updateStock() {

    }

    public void recover() {

    }

    public void produce(Producable production) {

    }

    public void calculateFlowValue() {

    }

    public void addNewUnit(Unit unit) {

    }

    public void addNewBuilding(BuildingType buildingType) {

    }

    public boolean belongsTo(Country country) {
        return false;
    }

    public boolean hasLandSquare(LandSquare landform) {
        return false;
    }
}
