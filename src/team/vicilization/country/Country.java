package team.vicilization.country;

import team.vicilization.gameitem.City;
import team.vicilization.gameitem.CityName;
import team.vicilization.gameitem.Unit;
import team.vicilization.gamemap.GameMap;
import team.vicilization.gamemap.LandSquare;
import team.vicilization.gamemap.LandformType;
import team.vicilization.mechanics.*;
import team.vicilization.util.Property;
import team.vicilization.util.Position;

import java.util.*;

public class Country {
    //========================Attributes======================//
    private LeaderName leaderName; //TODO change to Leader
    private Vector<City> cities;
    private Vector<Unit> units;

    private CountryName countryName;

    private Vector<Trader> traders;
    private int occupiedTradeRoutes;
    private int totalTradeRoutes;

    private HashMap<String, Integer> countryResource;
    private Vector<ScienceName> learntScience;
    private ScienceName currentScience;

    private Property flowValue;
    private Property stockValue;

    private Vector<CityName> availableNames;

    public Country(CountryName countryName) {
        this.countryName = countryName;
        this.leaderName = CountryConfig.LEADER_OF_COUNTRY.get(countryName);
        this.cities = new Vector<>();
        this.units = new Vector<>();
        this.traders = new Vector<>();
        this.occupiedTradeRoutes = 0;
        this.totalTradeRoutes = 0;

        this.countryResource = new HashMap<String, Integer>() {
        };
        this.currentScience = null;
        this.flowValue = new Property();
        this.stockValue = new Property();

        this.availableNames = new Vector<CityName>(
                CountryConfig.CITIES_OF_COUNTRY.get(this.countryName)
        );
        Collections.shuffle(availableNames);
    }

    public void endOfCurrentRound() {
        for (Unit unit : units) {
            unit.unitEndOfTurn();
        }
        for (City city : cities) {
            city.cityEndOfTurn();
        }
    }

    public void readyForNewRound() {
        // TODO 这里要执行计算存量流量、推进项目等一系列会在每一回合开始执行的任务
        for (Unit unit : units) {
            unit.unitStartTurn();
        }
        for (City city : cities) {
            city.cityStartTurn();
        }
        this.updateStock();
    }


    private void updateStock() {
        this.calculateFlowValue();
        this.stockValue.addProperty(this.flowValue);

        // TODO
        // TODO private?
        // TODO city stock/flow and country stock/flow

        this.finishScience();
    }

    private void pushProject() {
        // TODO Country: science, giant
        // TODO City: push project
    }

    private void calculateFlowValue() {
        Property temp = new Property();
        for (City city : cities) {
            // TODO
            //temp.addProperty(city.getFlowValue());
        }
        this.flowValue = temp;
    }

    /*
    private void calculateTradeMoney() {
        // delete or not
    }
    */

    public City buildNewCity(Position position, GameMap map, Country enemyCountry) {
        Vector<LandSquare> newTerritory = new Vector<>();
        int x = position.getX();
        int y = position.getY();

        newTerritory.add(map.getSquare(x, y));
        for (Position terr : CountryConfig.DEFAULT_TERRITORY) {
            if (map.isLegalPosition(x + terr.getX(), y + terr.getY())) {
                newTerritory.add(map.getSquare(x + terr.getX(), y + terr.getY()));
            }
        }
        for (City city : this.cities) {
            for (LandSquare square : city.getTerritory()) {
                newTerritory.remove(square);
            }
        }
        for (City city : enemyCountry.cities) {
            for (LandSquare square : city.getTerritory()) {
                newTerritory.remove(square);
            }
        }

        if (availableNames.size() == 0) {
            this.availableNames = CountryConfig.CITIES_OF_COUNTRY.get(this.countryName);
            Collections.shuffle(availableNames);
        }
        City tempCity = new City(this, position, this.availableNames.get(0), newTerritory);
        this.availableNames.remove(0);
        cities.add(tempCity);
        return tempCity;
    }

    public void occupyCity(City city) {
        this.cities.add(city);
    }

    public void loseCity(City city) {
        this.cities.remove(city);
    }

    public void addNewUnit(Unit unit) {
        this.units.add(unit);
    }

    public void deleteUnit(Unit unit) {
        this.units.remove(unit);
    }

    public void harvestLandform(Position position, LandformType landformType) {
        City city = cities.get(0);
        int distance = 2500;
        int temp;
        for (City c : cities) {
            if ((temp = Position.distanceSquare(position, c.getLocation())) < distance) {
                city = c;
                distance = temp;
            }
        }

        // TODO
        // city.getStockValue().addFlow();
    }

    public void selectScience(ScienceName scienceName) {
        this.currentScience = scienceName;
    }

    private void finishScience() {
        if ((this.currentScience != null)
                && (this.stockValue.getScience() >= ScienceConfig.SCIENCE_COST.get(this.currentScience))) {
            this.stockValue.setScience(this.stockValue.getScience() - ScienceConfig.SCIENCE_COST.get(this.currentScience));
            this.learntScience.add(this.currentScience);
            this.currentScience = null;
        }
    }

    /*
    public ResourceType harvestResource(LandformType landformType){
    }

    public void addTradeRoute(){
    }

    public void startTradeRoute(){
    }

    public void tradeAdvance(){
    }

    public void endTrade(){
    }
    */

    public void recruitGiant(Giant giant) {
        // TODO giantname??
    }

    public boolean judgeScienceVictory() {
        return this.learntScience.contains(ScienceName.AEROSPACE);
    }

    public boolean hasCity(City city) {
        return this.cities.contains(city);
    }

    //========================Get-Set Methods======================//

    public LeaderName getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(LeaderName leaderName) {
        this.leaderName = leaderName;
    }

    public Vector<City> getCities() {
        return cities;
    }

    public Vector<Unit> getUnits() {
        return units;
    }

    public Vector<Trader> getTraders() {
        return traders;
    }

    public CountryName getCountryName() {
        return this.countryName;
    }

    public int getOccupiedTradeRoutes() {
        return occupiedTradeRoutes;
    }

    public void setOccupiedTradeRoutes(int occupiedTradeRoutes) {
        this.occupiedTradeRoutes = occupiedTradeRoutes;
    }

    public int getTotalTradeRoutes() {
        return totalTradeRoutes;
    }

    public void setTotalTradeRoutes(int totalTradeRoutes) {
        this.totalTradeRoutes = totalTradeRoutes;
    }

    public HashMap<String, Integer> getCountryResource() {
        return countryResource;
    }
    //TODO Set country resources 的逻辑?

    public Vector<ScienceName> getLearntScience() {
        return learntScience;
    }

    public Property getFlowValue() {
        return flowValue;
    }

    public Property getStockValue() {
        return stockValue;
    }

}
