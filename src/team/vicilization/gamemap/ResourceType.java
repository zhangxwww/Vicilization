package team.vicilization.gamemap;

public enum ResourceType {

    NONE/*,
    HORSE,
    IRON,
    STONE,
    COPPER*/;

    public int getMobilityCost() {
        return GameMapConfig.RESOURCE_MOBILITY_COST.get(this);
    }

    public int getDefenceBuff() {
        return GameMapConfig.RESOURCE_DEFENCE_BUFF.get(this);
    }

    public int getProductivityYield() {
        return GameMapConfig.RESOURCE_PRODUCTIVITY.get(this);
    }

    public int getMoneyYield() {
        return GameMapConfig.RESOURCE_MONEY.get(this);
    }

    public int getFoodYield() {
        return GameMapConfig.RESOURCE_FOOD.get(this);
    }

    public int getScienceYield() {
        return GameMapConfig.RESOURCE_SCIENCE.get(this);
    }
}