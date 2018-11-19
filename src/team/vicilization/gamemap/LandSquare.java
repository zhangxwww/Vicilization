package team.vicilization.gamemap;

import javax.swing.*;

public class LandSquare extends JLabel {
    //========================Attributes======================//
    private TerrainType terrainType;
    private LandformType landformType;
    private ResourceType resourceType;
    private boolean isMarked;
    private int mobilityCost;
    private int defenceBuff;
    private int productivityYield;
    private int foodYield;
    private int moneyYield;
    private int scienceYield;

    //一旦初始化，terrain, landform 都不再变化
    void initLandSquare(TerrainType terrain,
                        LandformType landform,
                        ResourceType resource) {
        //TODO Calculate
        this.terrainType = terrain;
        this.landformType = landform;
        this.resourceType = resource;
    }

    ResourceType exploit(){
        ResourceType result = this.resourceType;
        this.resourceType = ResourceType.NONE;
        this.calculateAll();
        return result;
    }

    void mark(){
        this.isMarked = true;
    }

    void unmark(){
        this.isMarked = false;
    }

    //========================Calculate Methods======================//
    public void calculateAll(){
        this.calMobilityCost();
        this.calDefenceBuff();
        this.calFoodYield();
        this.calMoneyYield();
        this.calProductivityYield();
        this.calScienceYield();
    }      //The only public one;

    private void calMobilityCost() {
        this.mobilityCost = this.landformType.getMobilityCost()
                + this.terrainType.getMobilityCost()
                + this.resourceType.getMobilityCost();
    }

    private void calDefenceBuff() {
        this.defenceBuff = this.landformType.getDefenceBuff()
                + this.terrainType.getDefenceBuff()
                + this.resourceType.getDefenceBuff();
    }

    private void calProductivityYield() {
        this.productivityYield = this.landformType.getProductivityYield()
                + this.terrainType.getProductivityYield()
                + this.resourceType.getProductivityYield();
    }

    private void calFoodYield() {
        this.foodYield = this.landformType.getFoodYield()
                + this.terrainType.getFoodYield()
                + this.resourceType.getFoodYield();
    }

    private void calMoneyYield() {
        this.moneyYield = this.landformType.getMoneyYield()
                + this.terrainType.getMoneyYield()
                + this.resourceType.getMoneyYield();
    }

    private void calScienceYield() {
        this.scienceYield = this.landformType.getScienceYield()
                + this.terrainType.getScienceYield()
                + this.resourceType.getScienceYield();
    }

    //========================Get Methods======================//
    public LandformType getLandformType() {
        return landformType;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public boolean getMark() {
        return isMarked;
    }

    public int getMobilityCost() {
        return mobilityCost;
    }

    public int getDefenceBuff() {
        return defenceBuff;
    }

    public int getProductivityYield() {
        return productivityYield;
    }

    public int getFoodYield() {
        return foodYield;
    }

    public int getMoneyYield() {
        return moneyYield;
    }

    public int getScienceYield() {
        return scienceYield;
    }
}
