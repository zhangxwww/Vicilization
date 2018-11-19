package team.vicilization.gamemap;

public enum LandformType {
    GRASSLANDS,
    FOREST,
    RAINFOREST,
    FROZENGROUND,
    DESERT,
    MARSH;

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
