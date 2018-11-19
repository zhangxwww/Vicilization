package team.vicilization.country;

import team.vicilization.gameitem.City;
import team.vicilization.gameitem.Unit;

import java.util.HashMap;
import java.util.Vector;

import team.vicilization.gameitem.*;
import team.vicilization.mechanics.Leader;
import team.vicilization.mechanics.ScienceName;
import team.vicilization.mechanics.Trader;
import team.vicilization.util.Position;

public class Country {
    //========================Attributes======================//
    private Leader leader;
    private Vector<City> cities;
    private Vector<Unit> units;

    private CountryName countryName;

    private Vector<Trader> traders;
    private int occupiedTradeRoutes;
    private int totalTradeRoutes;

    private HashMap<String, Integer> countryResource;
    private Vector<ScienceName> learntScience;

    private CountryFlowValue flowValue;
    private CountryStockValue stockValue;  //TODO 接口文件中首字母？

    public Country(CountryName name) {
        // TODO
    }

    public void readyForNewRound() {
        // TODO 这里要执行计算存量流量、推进项目、城市恢复等一系列会在每一回合开始执行的任务
    }

    public void addNewUnit(Unit unit, Position position) {

    }

    public void buildNewCity(Position position) {

    }

    private void calculateFlowValue() {
        // TODO
    }

    private void calculateStockValue() {
        // TODO
    }

    public boolean judgeVectory() {
        // TODO
        return false;
    }

    //========================Get-Set Methods======================//

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
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
    //TODO 没有Set cities/units/traders 等Vector或其他类?

    public CountryName getCountryName() {
        return CountryName.INDIA;
        // return countryName;
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

    public CountryFlowValue getFlowValue() {
        return flowValue;
    }

    public CountryStockValue getStockValue() {
        return stockValue;
    }

    //TODO 向量的remove add等
}
