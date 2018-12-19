package team.vicilization.country;

import team.vicilization.gameitem.City;
import team.vicilization.gameitem.Unit;

import java.util.HashMap;
import java.util.Vector;

import team.vicilization.gameitem.*;
import team.vicilization.gamemap.LandformType;
import team.vicilization.gamemap.ResourceType;
import team.vicilization.mechanics.*;

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
    private ScienceName currentScience = null;
    // TODO current science name? Science是否常量？枚举？如何检索？

    private CountryFlowValue flowValue;
    private CountryStockValue stockValue;

    public Country(CountryName name) {
        this.countryName = name;

        // TODO 有

    }

    public void readyForNewRound() {
        // TODO 这里要执行计算存量流量、推进项目、城市恢复等一系列会在每一回合开始执行的任务
    }

    public void updateStock() {
        // TODO
        // TODO private?
        // TODO duplicate with calculateStockValue?
        // TODO city stock/flow and country stock/flow
    }

    public void pushProject() {

        // TODO
        // TODO private?
    }

    private void calculateFlowValue() {
        for(City city:this.cities){
            city.calculateFlowValue();
        }
        // TODO 如何拿到cityFlow? 一致性？
    }

    private void calculateStockValue() {
        // TODO
    }

    /*
    private void calculateTradeMoney() {
        // delete or not
    }
    */

    public void buildNewCity(Position position) {
        // TODO return city?
    }

    public void occupyCity(City city) {
        this.cities.add(city);
        // TODO need for position?
        // TODO need for re calculating?
    }

    public void loseCity(City city) {
        this.cities.remove(city);
        // TODO need for position?
        // TODO need for re calculating?
    }

    public void addNewUnit(Unit unit, Position position) {
        this.units.add(unit);
        // TODO need for position?
        // TODO need for re calculating?
        // TODO call city add?
    }

    public void deleteUnit(Unit unit) {
        this.units.remove(unit);
        // TODO need for position?
        // TODO need for re calculating?
    }

    public void selectScience(ScienceName scienceName) {
        this.currentScience = scienceName;
    }

    public ScienceName finishScience() {
        if(this.currentScience == null){
            return null;
        }else if(this.stockValue.getScience() < this.currentScience){
            return null;
        }else{
            ScienceName result = this.currentScience;
            this.currentScience = null;
            return result;
        }
        // TODO other science
        // TODO private
        // TODO how to find science?
        // TODO 一旦调用，科技就不见？null试探和返回值如何处理?
        // TODO 要求玩家必须选择在研究的science
    }

    /*
    public ResourceType harvestResource(LandformType landformType){
        // TODO modify in 接口？
        // TODO private
    }

    public void addTradeRoute(){
        // TODO private
    }

    public void startTradeRoute(){
        // TODO private
    }

    public void tradeAdvance(){
        // TODO private
    }

    public void endTrade(){
        // TODO private
    }
    */

    public void recruitGiant(Giant giant){
        // TODO giantname??
    }

    public boolean judgeVictory() {
        // TODO 什么是胜利条件
        return false;
    }

    public boolean hasCity(City city) {
        return this.cities.contains(city);
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

        return this.countryName;
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

    //=========================remove add========================//
    //TODO 向量的remove add等
}
