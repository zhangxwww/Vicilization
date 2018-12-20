package team.vicilization.gameitem;

public class CityFlowValue {


    private int productivity ;
    private int money ;
    private int food ;
    private int science ;
    private int scientistValue ;
    private int traderValue ;
    private int engineerValue ;

    public CityFlowValue(){
        this.productivity=0;
        this.money=0;
        this.science=0;
        this.food=0;
        this.scientistValue=0;
        this.traderValue=0;
        this.engineerValue=0;
    }

    public void addValue(CityFlowValue flowValue){
        this.productivity+=flowValue.productivity;
        this.money+=flowValue.money;
        this.science+=flowValue.science;
        this.food+=flowValue.food;
        this.scientistValue+=flowValue.scientistValue;
        this.traderValue+=flowValue.traderValue;
        this.engineerValue+=flowValue.engineerValue;
    }


    public int getProductivity() {
        return productivity;
    }

    public void setProductivity(int productivity) {
        this.productivity = productivity;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
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
