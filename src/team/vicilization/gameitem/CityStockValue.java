package team.vicilization.gameitem;

public class CityStockValue {
    private int producticity;
    private int food;
    private int science;
    private int scientistValue;
    private int traderValue;
    private int engineerValue;

    public CityStockValue(){
        this.producticity=0;
        this.money=0;
        this.food=0;
        this.science=0;
        this.scientistValue=0;
        this.traderValue=0;
        this.engineerValue=0;
    }

    public void addFlow(CityFlowValue cityFlowValue){
        this.engineerValue+=cityFlowValue.getEngineerValue();
        this.traderValue+=cityFlowValue.getTraderValue();
        this.scientistValue+=cityFlowValue.getScientistValue();
        this.science+=cityFlowValue.getScience();
        this.food+=cityFlowValue.getFood();
        this.money+=cityFlowValue.getMoney();
        this.producticity+=cityFlowValue.getProductivity();
    }
    //===================get set==================//


    public int getEngineerValue() {
        return engineerValue;
    }

    public int getFood() {
        return food;
    }

    public int getMoney() {
        return money;
    }

    public int getProducticity() {
        return producticity;
    }

    public int getScience() {
        return science;
    }

    public int getScientistValue() {
        return scientistValue;
    }

    public int getTraderValue() {
        return traderValue;
    }

    public void setEngineerValue(int engineerValue) {
        this.engineerValue = engineerValue;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setProducticity(int producticity) {
        this.producticity = producticity;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public void setScientistValue(int scientistValue) {
        this.scientistValue = scientistValue;
    }

    public void setTraderValue(int traderValue) {
        this.traderValue = traderValue;
    }

}
