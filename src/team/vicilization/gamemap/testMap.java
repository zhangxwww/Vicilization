package team.vicilization.gamemap;

public class testMap {
    public static void main(String[] args) {
        GameMap testMap = new GameMap();
        testMap.testPrint();

        System.out.println(testMap.getSquare(3,4).getTerrainType());
        System.out.println(testMap.getSquare(3,4).getDefenceBuff());
        System.out.println(testMap.getSquare(3,4).getFoodYield());
        System.out.println(testMap.getSquare(3,4).getMobilityCost());
        System.out.println(testMap.getSquare(3,4).getProductivityYield());

    }
}