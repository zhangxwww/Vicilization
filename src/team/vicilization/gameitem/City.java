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

    private ProducableInfo producingItem;
    //private Producable productingItem;
    private int cityAttack;
    private int cityDefence;
    private int cityHealth;
    private int recovery;
    private Vector<BuildingType> allowedBuildings;
    private Vector<Unit> allowedUnits;


    public boolean isProducing;

    public City(Country country, Position position, CityName name,Vector<LandSquare> territory) {

        this.name = name;
        this.population = 1;
        this.location = position;
        this.country = country;
        this.flowValue = new CityFlowValue();
        this.stockValue = new CityStockValue();
        this.cityAttack = 2;
        this.cityDefence = 2;
        this.cityHealth = 100;
        this.recovery = 10;

        this.territory=territory;
        this.constructedBuildings=new Vector<BuildingType>();


        this.isProducing=false;

        this.territory = territory;

    }

    //update vectors
    public void updateConstructedBuildings(BuildingType buildingType){
        constructedBuildings.add(buildingType);
    }
    public void calculateAllowedBuildings(){
        Vector<BuildingType> tempAllowedBuildings=new Vector<BuildingType>(3);
        tempAllowedBuildings.add(BuildingType.ACADEMY);
        tempAllowedBuildings.add(BuildingType.COMMERCIAL_CERTER);
        tempAllowedBuildings.add(BuildingType.INDUSTRIAL_PARK);
        for(BuildingType type:tempAllowedBuildings){
            //科技if(GameItemConfig.)
            //已建造
            tempAllowedBuildings.remove(type);
        }
        this.allowedBuildings=tempAllowedBuildings;
    }
    public void calculateAllowedUnits(){
        Vector<UnitSubType> tempAllowedUnits=new Vector<UnitSubType>(20);
        tempAllowedUnits.add(UnitSubType.ARCHER);
        tempAllowedUnits.add(UnitSubType.CONSTRUCTOR);
        if (population==1){
            tempAllowedUnits.remove(UnitSubType.EXPLORER);
        }
        for (UnitSubType type:tempAllowedUnits){
            //科技
        }
    }


    private void calculateFlowValue() {
        this.flowValue = new CityFlowValue();
        //地块产出
        CityFlowValue territoryFlowValue = new CityFlowValue();
        for (LandSquare land : territory) {
            territoryFlowValue.setFood(land.getFoodYield());
            territoryFlowValue.setMoney(land.getMoneyYield());
            territoryFlowValue.setScience(land.getScienceYield());
            territoryFlowValue.setProductivity(land.getProductivityYield());
        }
        this.flowValue.addValue(territoryFlowValue);
        //人口产出
        CityFlowValue populationFlowValue = new CityFlowValue();
        populationFlowValue.setProductivity(this.population);
        populationFlowValue.setScience(this.population);
        populationFlowValue.setMoney(this.population);
        populationFlowValue.setFood(-this.population);
        this.flowValue.addValue(populationFlowValue);
        //建筑产出
        CityFlowValue buildingFlowValue = new CityFlowValue();
        for (BuildingType buildingType : constructedBuildings) {
            buildingFlowValue.addValue(new CityFlowValue(buildingType));
        }
        this.flowValue.addValue(buildingFlowValue);
    }

    public void cityEndOfTurn(){
        //重置移动力

    }
    public void cityStartTurn(){

    }



    public void updateStock() {
        //TO DO details
        calculateFlowValue();
        stockValue.addFlow(flowValue);
        //处理生产力  产出
        if(stockValue.getProducticity()>=producingItem.getProductivityCost()){
            System.out.println("Unit Complete");
            stockValue.setProducticity(stockValue.getProducticity()-producingItem.getProductivityCost());
            //producingItem.finish;
        }
        //处理食物  人口
        if(stockValue.getFood()>=5){
            while (stockValue.getFood()>=5) {
                this.population += 1;
                stockValue.setFood(stockValue.getFood() - 5);
            }
        }

        if(stockValue.getFood()<=-5){
            while(stockValue.getFood()<=-5){
                if(this.population==1){
                    break;
                }
                this.population-=1;
                stockValue.setFood(stockValue.getFood()+5);
            }
        }
        //处理钱
        //处理科技和各种点数

    }

    public void recover() {
        int initHealth = 100;
        if (cityHealth < initHealth - recovery) {
            cityHealth += recovery;
        } else {
            cityHealth = initHealth;
        }
    }

    //public void produce(Producable production) {

    //}


    public void produce(UnitSubType type){
        this.producingItem=new UnitInfo(type);
        this.setIsProducing(true);
    }
    public void produce(BuildingType type){
        this.producingItem=new BuildingInfo(type);
        this.setIsProducing(true);
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
    public boolean getIsProducing(){
        return this.isProducing;
    }
    public void setIsProducing(boolean b){
        this.isProducing=b;
    }
}
