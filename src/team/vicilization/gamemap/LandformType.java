package team.vicilization.gamemap;

public enum LandformType {
    NONE,
    GRASSLANDS,
    FOREST,
    RAINFOREST,
    FROZENGROUND,
    DESERT,
    MARSH;
    //其中NONE用于表示被山河湖覆盖地形后留下的地貌

    public int getMobilityCost() {
        return GameMapConfig.LANDFORM_MOBILITY_COST.get(this);
    }

    public int getDefenceBuff() {
        return GameMapConfig.LANDFORM_DEFENCE_BUFF.get(this);
    }

    public int getProductivityYield() {
        return GameMapConfig.LANDFORM_PRODUCTIVITY.get(this);
    }

    public int getMoneyYield() {
        return GameMapConfig.LANDFORM_MONEY.get(this);
    }

    public int getFoodYield() {
        return GameMapConfig.LANDFORM_FOOD.get(this);
    }

    public int getScienceYield() {
        return GameMapConfig.LANDFORM_SCIENCE.get(this);
    }
}
