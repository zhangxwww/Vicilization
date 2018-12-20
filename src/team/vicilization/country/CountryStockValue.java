package team.vicilization.country;

public class CountryStockValue {
    private int money;
    private int science;
    private int scientistValue;
    private int traderValue;
    private int engineerValue;

    public CountryStockValue(){
        this.money = 0;
        this.science = 0;
        this.scientistValue = 0;
        this.traderValue = 0;
        this.engineerValue = 0;
    }

    //=======================get/set methods==================//
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getScientistValue() {
        return scientistValue;
    }

    public void setScientistValue(int scientistValue) {
        this.scientistValue = scientistValue;
    }

    public int getTraderValue() {
        return traderValue;
    }

    public void setTraderValue(int traderValue) {
        this.traderValue = traderValue;
    }

    public int getEngineerValue() {
        return engineerValue;
    }

    public void setEngineerValue(int engineerValue) {
        this.engineerValue = engineerValue;
    }
}
