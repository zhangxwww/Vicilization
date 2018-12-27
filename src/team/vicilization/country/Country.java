package team.vicilization.country;

import team.vicilization.gameitem.city.City;
import team.vicilization.gameitem.city.CityName;
import team.vicilization.gameitem.unit.*;
import team.vicilization.gamemap.*;
import team.vicilization.mechanics.leader.LeaderName;
import team.vicilization.mechanics.giant.*;
import team.vicilization.mechanics.science.ScienceConfig;
import team.vicilization.mechanics.science.ScienceName;
import team.vicilization.mechanics.trader.Trader;
import team.vicilization.util.Property;
import team.vicilization.util.Position;

import java.util.*;

public class Country {
    //========================Attributes======================//
    private LeaderName leaderName;
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

    private GiantName givenupScientist;
    private GiantName givenupEconomist;
    private GiantName givenupEngineer;

    public Country(CountryName countryName) {
        this.countryName = countryName;
        this.leaderName = CountryConfig.LEADER_OF_COUNTRY.get(countryName);
        this.cities = new Vector<>();
        this.units = new Vector<>();
        this.traders = new Vector<>();
        this.occupiedTradeRoutes = 0;
        this.totalTradeRoutes = 0;

        this.learntScience = new Vector<>();
        this.learntScience.add(ScienceName.NONE);

        this.countryResource = new HashMap<String, Integer>() {
        };
        this.currentScience = ScienceName.ARITHMETIC;
        this.flowValue = new Property();
        this.stockValue = new Property();

        this.availableNames = new Vector<CityName>(
                CountryConfig.CITIES_OF_COUNTRY.get(this.countryName)
        );
        Collections.shuffle(availableNames);

        this.givenupScientist = null;
        this.givenupEconomist = null;
        this.givenupEngineer = null;
    }

    public void endOfCurrentRound() {
        for (Unit u : units) {
            u.unitEndOfTurn();
        }
        for (City city : cities) {
            city.cityEndOfTurn();
        }
    }

    public void readyForNewRound(GameMap map, Country enemyCountry) {
        UnitSubType temp;
        for (City city : cities) {
            temp = city.cityStartTurn();
            switch (temp) {
                case CONSTRUCTOR:
                    this.addNewUnit(
                            new Constructor(
                                    this.nearestAvailable(city.getLocation(), map, enemyCountry),
                                    this));
                    break;
                case ARCHER:
                    this.addNewUnit(
                            new Archer(
                                    this.nearestAvailable(city.getLocation(), map, enemyCountry),
                                    this));
                    break;
                case KNIGHT:
                    this.addNewUnit(
                            new Knight(
                                    this.nearestAvailable(city.getLocation(), map, enemyCountry),
                                    this));
                    break;
                case FOOTMAN:
                    this.addNewUnit(
                            new Footman(
                                    this.nearestAvailable(city.getLocation(), map, enemyCountry),
                                    this));
                    break;
                case EXPLORER:
                    this.addNewUnit(
                            new Explorer(
                                    this.nearestAvailable(city.getLocation(), map, enemyCountry),
                                    this));
                    break;
                case SPEARMAN:
                    this.addNewUnit(
                            new Spearman(
                                    this.nearestAvailable(city.getLocation(), map, enemyCountry),
                                    this));
                    break;
                case ASSASSIN:
                    this.addNewUnit(
                            new Assassin(
                                    this.nearestAvailable(city.getLocation(), map, enemyCountry),
                                    this));
                    break;
                case SWORDSMAN:
                    this.addNewUnit(
                            new SwordsMan(
                                    this.nearestAvailable(city.getLocation(), map, enemyCountry),
                                    this));
                    break;
                default:
                    break;
            }
        }
        for (Unit u : units) {
            u.unitStartTurn();
        }
        this.updateStock();
        this.finishScience();
    }

    public void undateFlow() {
        this.calculateFlowValue();
    }

    public boolean hasLandSquare(LandSquare landSquare) {
        for (City city : cities) {
            if (city.hasLandSquare(landSquare)) {
                return true;
            }
        }
        return false;
    }

    private Position nearestAvailable(Position root, GameMap map, Country enemyCountry) {
        Vector<LandSquare> availablePosition = new Vector<>();
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                if (map.getSquare(i, j).getTerrainType() != TerrainType.RIDGE) {
                    availablePosition.add(map.getSquare(i, j));
                }
            }
        }
        for (Unit unit : this.units) {
            availablePosition.remove(map.getSquare(unit.getPosition()));
        }
        for (Unit unit : enemyCountry.units) {
            availablePosition.remove(map.getSquare(unit.getPosition()));
        }
        for (City city : this.cities) {
            availablePosition.remove(map.getSquare(city.getLocation()));
        }
        for (City city : enemyCountry.cities) {
            availablePosition.remove(map.getSquare(city.getLocation()));
        }
        Position position = availablePosition.get(0).getPosition();
        int distance = 2500;
        int temp;
        for (LandSquare square : availablePosition) {
            if ((temp = Position.distanceSquare(root, square.getPosition())) < distance) {
                distance = temp;
                position = square.getPosition();
            }
        }
        return position;
    }

    private void updateStock() {
        this.calculateFlowValue();
        this.stockValue.addProperty(this.flowValue);
    }

    private void calculateFlowValue() {
        this.flowValue = new Property();
        for (City city : cities) {
            city.updateFlowValue();
            this.flowValue.addProperty(city.getFlowValue());
        }
    }

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
        this.calculateFlowValue();
        return tempCity;
    }

    public void occupyCity(City city) {
        this.cities.add(city);
        city.setCountry(this);
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
        city.getStockValue().addProperty(GameMapConfig.LANDFORM_HARVEST.get(landformType));
    }

    /*
        public void selectScience(ScienceName scienceName) {
            this.currentScience = scienceName;
        }
    */

    private void finishScience() {
        if ((this.currentScience != null)
                && (this.stockValue.getScience() >= ScienceConfig.SCIENCE_COST.get(this.currentScience))) {
            this.stockValue.setScience(this.stockValue.getScience() - ScienceConfig.SCIENCE_COST.get(this.currentScience));
            this.learntScience.add(this.currentScience);
            this.currentScience = ScienceConfig.NEXT_SCIENCE.get(this.currentScience);
        }
    }

    public void recruitGiant(GiantName giantName) {
        GiantType type = GiantConfig.GIANT_NAME_TO_TYPE.get(giantName);
        if ((type == GiantType.ECONOMIST) || (type == GiantType.SCIENTIST)) {
            this.stockValue.addProperty(GiantConfig.GIANT_TYPE_BONUS.get(type));
        } else {
            this.cities.get(0).getStockValue().addProperty(GiantConfig.GIANT_TYPE_BONUS.get(type));
        }
        switch (GiantConfig.GIANT_NAME_TO_TYPE.get(giantName)) {
            case SCIENTIST:
                this.getStockValue().setScientistValue(getStockValue().getScientistValue()
                        - GiantConfig.GIANT_TYPE_COST.get(GiantType.SCIENTIST).getScientistValue());
                break;
            case ECONOMIST:
                this.getStockValue().setTraderValue(getStockValue().getTraderValue()
                        - GiantConfig.GIANT_TYPE_COST.get(GiantType.ECONOMIST).getTraderValue());
                break;
            case ENGINEER:
                this.getStockValue().setEngineerValue(getStockValue().getEngineerValue()
                        - GiantConfig.GIANT_TYPE_COST.get(GiantType.ENGINEER).getEngineerValue());
                break;
            default:
                break;
        }
    }

    public boolean judgeScienceVictory() {
        return this.learntScience.contains(ScienceName.BUDDHISM);
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

    public Vector<ScienceName> getLearntScience() {
        return learntScience;
    }

    public Property getFlowValue() {
        return flowValue;
    }

    public Property getStockValue() {
        return stockValue;
    }

    public ScienceName getCurrentScience() {
        return currentScience;
    }

    public GiantName getGivenupScientist() {
        return givenupScientist;
    }

    public GiantName getGivenupEconomist() {
        return givenupEconomist;
    }

    public GiantName getGivenupEngineer() {
        return givenupEngineer;
    }

    public void setGivenupScientist(GiantName givenupScientist) {
        this.givenupScientist = givenupScientist;
    }

    public void setGivenupEconomist(GiantName givenupEconomist) {
        this.givenupEconomist = givenupEconomist;
    }

    public void setGivenupEngineer(GiantName givenupEngineer) {
        this.givenupEngineer = givenupEngineer;
    }
}
