package team.vicilization.gameitem;

import team.vicilization.country.Country;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;

import javax.swing.*;
import java.util.Vector;

public class City {
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

    public City(Country country, Position position,CityName name, Vector<LandSquare> territory) {

        this.name=name;
        this.population=1;
        this.location=position;
        this.country=country;
        this.flowValue=new CityFlowValue();
        this.stockValue=new CityStockValue();
        this.cityAttack=2;
        this.cityDefence=2;
        this.cityHealth=100;
        this.recovery=10;

        this.territory = territory;

    }

    private void calculateFlowValue() {
        this.flowValue=new CityFlowValue();
        //地块产出
        CityFlowValue territoryFlowValue=new CityFlowValue();
        for(LandSquare land:territory){
            territoryFlowValue.setFood(land.getFoodYield());
            territoryFlowValue.setMoney(land.getMoneyYield());
            territoryFlowValue.setScience(land.getScienceYield());
            territoryFlowValue.setProductivity(land.getProductivityYield());
        }
        this.flowValue.addValue(territoryFlowValue);
        //人口产出
        CityFlowValue populationFlowValue=new CityFlowValue();
        populationFlowValue.setProductivity(this.population);
        populationFlowValue.setScience(this.population);
        populationFlowValue.setMoney(this.population);
        populationFlowValue.setFood(-this.population);
        this.flowValue.addValue(populationFlowValue);
        //建筑产出
        CityFlowValue buildingFlowValue=new CityFlowValue();
        if(constructedBuildings.contains(BuildingType.ACADEMY)){
            buildingFlowValue.setScience(5);
            buildingFlowValue.setScientistValue(5);
        }else if(constructedBuildings.contains(BuildingType.COMMERCIAL_CERTER)){
            buildingFlowValue.setMoney(5);
            buildingFlowValue.setTraderValue(5);
        }else if(constructedBuildings.contains(BuildingType.INDUSTRIAL_PARK)){
            buildingFlowValue.setProductivity(5);
            buildingFlowValue.setEngineerValue(5);
        }
        this.flowValue.addValue(buildingFlowValue);


    }


    public void updateStock() {
        stockValue.addFlow(flowValue);
    }

    public void recover(){
        int initHealth=100;
        if (cityHealth<initHealth-recovery){
            cityHealth+=recovery;
        }else {
            cityHealth=initHealth;
        }
    }

    public void produce(Producable production) {

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

    public Vector<LandSquare> getTerritory() {
        return territory;
    }

    public Position getLocation() {
        return location;
    }

    public CityStockValue getStockValue() {
        return stockValue;
    }
}
