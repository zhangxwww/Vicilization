package team.vicilization.gamemap;

public class testMap {
    public static void main(String[] args) {
        // 地图测试类，打印地形图和地貌图

        GameMap testMap = new GameMap();

        System.out.println(testMap.getSquare(3, 4).getTerrainType());
        System.out.println(testMap.getSquare(3, 4).getDefenceBuff());
        System.out.println(testMap.getSquare(3, 4).getFoodYield());
        System.out.println(testMap.getSquare(3, 4).getMobilityCost());
        System.out.println(testMap.getSquare(3, 4).getProductivityYield());

        System.out.println("Printing terrainMap");
        for (int i = 0; i < GameMapConfig.MAP_HEIGHT; i++) {
            for (int j = 0; j < GameMapConfig.MAP_WIDTH; j++) {
                System.out.print(testMap.getSquare(i, j).getTerrainType());
                System.out.print("\t");
            }
            System.out.print('\n');
        }

        System.out.println("Printing landformMap");
        for (int i = 0; i < GameMapConfig.MAP_HEIGHT; i++) {
            for (int j = 0; j < GameMapConfig.MAP_WIDTH; j++) {
                System.out.print(testMap.getSquare(i, j).getLandformType());
                System.out.print("\t");
            }
            System.out.print('\n');
        }


    }
}