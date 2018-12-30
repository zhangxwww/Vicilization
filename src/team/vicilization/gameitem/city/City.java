package team.vicilization.gameitem.city;

import team.vicilization.country.Country;
import team.vicilization.gameitem.Fightable;
import team.vicilization.gameitem.GameItemConfig;
import team.vicilization.gameitem.ProducableInfo;
import team.vicilization.gameitem.building.BuildingInfo;
import team.vicilization.gameitem.building.BuildingType;
import team.vicilization.gameitem.unit.UnitInfo;
import team.vicilization.gameitem.unit.UnitSubType;
import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.util.Position;
import team.vicilization.util.Property;

import java.util.Vector;

public class City implements Fightable {

    //-------------------------------------Attributes

    private CityName name;
    private int population;
    private Position location;
    private Country country;
    private Property flowValue;
    private Property stockValue;
    private Vector<LandSquare> territory;
    private Vector<BuildingType> constructedBuildings;

    private ProducableInfo producingItem;
    private BuildingType producingBuilding;
    private UnitSubType producingUnit;

    private int cityAttack;
    private int cityDefence;
    private int cityHealth;
    private int recovery;
    private Vector<BuildingType> allowedBuildings;
    private Vector<UnitSubType> allowedUnits;

    public boolean isProducing;

    //--------------------------------------------构造函数
    public City(Country country, Position position, CityName name, Vector<LandSquare> territory) {

        this.name = name;
        this.population = 1;
        this.location = position;
        this.country = country;
        this.flowValue = new Property();
        this.stockValue = new Property();
        this.territory = territory;
        this.constructedBuildings = new Vector<BuildingType>();

        this.producingItem = new ProducableInfo();
        this.producingUnit = UnitSubType.NONE;
        this.producingBuilding = BuildingType.NONE;

        this.cityAttack = 2;
        this.cityDefence = 2;
        this.cityHealth = 100;
        this.recovery = 10;

        this.allowedUnits = new Vector<>();
        this.allowedBuildings = new Vector<>();
        this.calculateAllowedBuildings();
        this.calculateAllowedUnits();

        this.isProducing = false;
    }

    //-------------------------------------update Attributes
    public void calculateAllowedBuildings() {
        this.allowedBuildings.clear();
        Vector<BuildingType> tempAllowedBuildings = new Vector<BuildingType>(3);
        tempAllowedBuildings.add(BuildingType.ACADEMY);
        tempAllowedBuildings.add(BuildingType.COMMERCIAL_CERTER);
        tempAllowedBuildings.add(BuildingType.INDUSTRIAL_PARK);
        for (BuildingType type : tempAllowedBuildings) {
            if (!constructedBuildings.contains(type) && !this.producingBuilding.equals(type)
                    && country.getLearntScience().contains(GameItemConfig.BUILDING_REQUIRED_SCIENCE.get(type))) {
                this.allowedBuildings.add(type);
            }
            //已建造
            //正在建造
        }
    }

    public void calculateAllowedUnits() {
        this.allowedUnits.clear();
        Vector<UnitSubType> tempAllowedUnits = new Vector<UnitSubType>(20);
        tempAllowedUnits.add(UnitSubType.ARCHER);
        tempAllowedUnits.add(UnitSubType.CONSTRUCTOR);
        tempAllowedUnits.add(UnitSubType.KNIGHT);
        tempAllowedUnits.add(UnitSubType.FOOTMAN);
        tempAllowedUnits.add(UnitSubType.ASSASSIN);
        tempAllowedUnits.add(UnitSubType.EXPLORER);
        tempAllowedUnits.add(UnitSubType.SPEARMAN);
        tempAllowedUnits.add(UnitSubType.SWORDSMAN);
        for (UnitSubType type : tempAllowedUnits) {
            if (country.getLearntScience().contains(GameItemConfig.UNIT_REQUIRED_SCIENCE.get(type))) {
                this.allowedUnits.add(type);
            }
        }
        if (population < 2) {
            this.allowedUnits.remove(UnitSubType.EXPLORER);
        }
    }

    public void updateFlowValue() {
        this.calculateFlowValue();
    }

    private void calculateFlowValue() {
        this.flowValue = new Property();
        //地块产出
        Property territoryFlowValue = new Property();
        for (LandSquare land : territory) {
            territoryFlowValue.setFood(territoryFlowValue.getFood() + land.getFoodYield());
            territoryFlowValue.setMoney(territoryFlowValue.getMoney() + land.getMoneyYield());
            territoryFlowValue.setScience(territoryFlowValue.getScience() + land.getScienceYield());
            territoryFlowValue.setProductivity(territoryFlowValue.getProductivity() + land.getProductivityYield());
        }
        this.flowValue.addProperty(territoryFlowValue);
        //人口产出
        Property populationFlowValue = new Property();
        populationFlowValue.setProductivity(this.population);
        populationFlowValue.setScience(this.population);
        populationFlowValue.setMoney(this.population);
        populationFlowValue.setFood(-this.population * 20);
        this.flowValue.addProperty(populationFlowValue);
        //建筑产出
        Property buildingFlowValue = new Property();
        for (BuildingType buildingType : constructedBuildings) {
            buildingFlowValue.addProperty(new Property(buildingType));
        }
        this.flowValue.addProperty(buildingFlowValue);
    }

    private void updateStock() {
        calculateFlowValue();
        stockValue.addProperty(flowValue);

        //处理生产力  产出


        //处理食物  人口
        if (stockValue.getFood() >= 150) {
            while (stockValue.getFood() >= 150) {
                this.population += 1;
                stockValue.setFood(stockValue.getFood() - 150);
            }
        }

        if (stockValue.getFood() <= 0) {
            while (stockValue.getFood() <= 0) {
                if (this.population == 1) {
                    break;
                }
                this.population -= 1;
                stockValue.setFood(stockValue.getFood() + 140);
            }
        }
        //处理钱
        //处理科技和各种点数

    }

    private void recover() {
        int initHealth = 100;
        if (cityHealth < initHealth - this.getRecovery()) {
            cityHealth += this.getRecovery();
        } else {
            cityHealth = initHealth;
        }
    }

    //------------------------------------------End/Start Turn
    public void cityEndOfTurn() {
        this.updateStock();
        this.recover();
        //重置移动力

    }

    public UnitSubType cityStartTurn() {
        finishProduceBuilding();
        return finishProduceUnit();
    }

    //----------------------------------------produce
    public void produce(UnitSubType type) {
        this.producingItem = new UnitInfo(type);
        this.producingUnit = type;
        this.setIsProducing(true);
    }

    public void produce(BuildingType type) {
        this.producingItem = new BuildingInfo(type);
        this.setIsProducing(true);
        this.producingBuilding = type;
    }

    private void finishProduceBuilding() {
        if (producingBuilding != BuildingType.NONE) {
            if (stockValue.getProductivity() >= producingItem.getProductivityCost()) {
                stockValue.setProductivity(stockValue.getProductivity() - producingItem.getProductivityCost());
                if (this.producingUnit == UnitSubType.NONE) {
                    constructedBuildings.add(this.producingBuilding);
                    this.isProducing = false;
                    this.producingBuilding = BuildingType.NONE;
                }
            }
        }
    }

    private UnitSubType finishProduceUnit() {
        if (producingUnit != UnitSubType.NONE) {
            if (stockValue.getProductivity() >= producingItem.getProductivityCost()) {
                stockValue.setProductivity(stockValue.getProductivity() - producingItem.getProductivityCost());
                if (this.producingBuilding == BuildingType.NONE) {
                    UnitSubType unitSubType = this.producingUnit;
                    if (unitSubType == UnitSubType.EXPLORER) {
                        this.setPopulation(getPopulation() - 1);
                    }
                    this.isProducing = false;
                    this.producingUnit = UnitSubType.NONE;
                    return unitSubType;
                } else {
                    return UnitSubType.NONE;
                }
                //producingItem.finish;
            }
        }
        return UnitSubType.NONE;
    }

    public boolean belongsTo(Country country) {
        return this.country == country;
    }

    public boolean hasLandSquare(LandSquare landSquare) {
        return this.territory.contains(landSquare);
    }

    //------------------------------------------Fightable

    @Override
    public int getAttack() {
        this.cityAttack = 5 * this.getPopulation();
        return cityAttack;
    }

    @Override
    public int getDefence() {
        this.cityDefence = 5 * this.getPopulation();
        return cityDefence;
    }

    @Override
    public int getHealth() {
        return cityHealth;
    }

    public Vector<LandSquare> getAttackRange(GameMap map) {
        Position currentLocation = this.getLocation();
        Vector<LandSquare> availableSquare = new Vector<LandSquare>();
        int attackRange = 2;
        for (int j = 0; j < attackRange; j++) {
            for (int i = 0; i <= j; i++) {
                Position p1 = new Position(currentLocation.getX() + i, currentLocation.getY() + attackRange - i);
                Position p2 = new Position(currentLocation.getX() - i, currentLocation.getY() - attackRange + i);
                Position p3 = new Position(currentLocation.getX() + attackRange - i, currentLocation.getY() - i);
                Position p4 = new Position(currentLocation.getX() - attackRange + i, currentLocation.getY() + i);
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
            this.setCityHealth(0);
            this.die();
        } else {
            this.setCityHealth(this.getHealth() - damage);
        }
    }

    @Override
    public void die() {
        this.country.loseCity(this);
    }

    @Override
    public boolean isDied() {
        return this.getHealth() <= 0;
    }

    //------------------------------------------get/set

    public CityName getCityName() {
        return name;
    }

    public boolean isProducing() {
        return this.isProducing;
    }

    public void setIsProducing(boolean b) {
        this.isProducing = b;
    }

    public Property getFlowValue() {
        return flowValue;
    }

    public Property getStockValue() {
        return stockValue;
    }

    public int getPopulation() {
        return population;
    }

    public BuildingType getProducingBuilding() {
        return producingBuilding;
    }

    public ProducableInfo getProducingItem() {
        return producingItem;
    }

    public Country getCountry() {
        return country;
    }

    public UnitSubType getProducingUnit() {
        return producingUnit;
    }

    public Vector<BuildingType> getConstructedBuildings() {
        return constructedBuildings;
    }

    public Vector<BuildingType> getAllowedBuildings() {
        calculateAllowedBuildings();
        return allowedBuildings;
    }

    public Vector<LandSquare> getTerritory() {
        return territory;
    }

    public Vector<UnitSubType> getAllowedUnits() {
        calculateAllowedUnits();
        return allowedUnits;
    }

    public void setCityHealth(int cityHealth) {
        this.cityHealth = cityHealth;
    }

    public Position getLocation() {
        return location;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getRecovery() {
        this.recovery = 10 + 2 * this.getPopulation();
        return recovery;
    }
}
