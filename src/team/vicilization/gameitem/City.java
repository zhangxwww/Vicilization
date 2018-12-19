package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import javax.swing.*;
import java.util.Vector;

public class City extends JButton {
    private CityName name;
    private int population;
    private Position location;
    private Country country;
    private CityFlowValue flowValue;
    private CityStockValue stockValue;
    private Vector<LandSquare> territory;
    private Vector<BuildingType> constructedBuildings;
    private Producable productingItem;
    private int cityAttack;
    private int cityDefence;
    private int cityHealth;
    private int recovery;
    private Vector<BuildingType> allowedBuildings;
    private Vector<Unit> allowedUnits;

    public City(Country country, Position position) {

        this.name=CityName.BOMBAY;
        this.population=1;
        this.location=position;
        this.country=country;
        this.flowValue=new CityFlowValue();
        this.stockValue=new CityStockValue();
        this.cityAttack=2;
        this.cityDefence=2;
        this.cityHealth=100;
        this.recovery=10;


    }

    public void updateStock() {

    }

    public void recover() {

        this.cityHealth+=this.recovery;

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
