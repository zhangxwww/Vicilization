package team.vicilization.gamemap;

import team.vicilization.util.Position;

import javax.swing.*;

public class LandSquare {
    //========================Attributes======================//
    private TerrainType terrainType;
    private LandformType landformType;
    private ResourceType resourceType;
    private Position position;
    private int mobilityCost;
    private int defenceBuff;
    private int productivityYield;
    private int foodYield;
    private int moneyYield;
    private int scienceYield;

    void initLandSquare(TerrainType terrain,
                        LandformType landform,
                        ResourceType resource,
                        Position position) {
        this.terrainType = terrain;
        this.landformType = landform;
        this.resourceType = resource;
        this.position = position;

        this.calculateAll();
    }

    // ===================开采Resource================//
    // resource未能按计划完成
    ResourceType exploit() {
        ResourceType result = this.resourceType;
        this.resourceType = ResourceType.NONE;
        this.calculateAll();
        return result;
    }

    // ===================清理Landform================//
    // 建造者可清除特定地貌，使附近城市获得一定加成，清理方法如下
    public void harvested() {
        if (Math.random() > GameMapConfig.RAND_LEVEL0) {
            this.landformType = LandformType.DESERT;
        } else {
            this.landformType = LandformType.GRASSLANDS;
        }
        this.calculateAll();
    }

    //=======================Calculate Methods=====================//
    // 为便于计算属性值，用calculateAll一次性调用全部计算函数更新属性值
    private void calculateAll() {
        this.calMobilityCost();
        this.calDefenceBuff();
        this.calFoodYield();
        this.calMoneyYield();
        this.calProductivityYield();
        this.calScienceYield();
    }

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

    public Position getPosition() {
        return position;
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
