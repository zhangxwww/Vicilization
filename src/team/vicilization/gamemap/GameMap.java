package team.vicilization.gamemap;

import java.util.Vector;

public class GameMap {
    //先取一列，再取列中每一行
    private Vector<Vector<LandSquare>> landSquares = new Vector<>(50);
    private Vector<Vector<LandformType>> landformMap = new Vector<>(50);
    private Vector<Vector<TerrainType>> terrainMap = new Vector<>(50);
    private Vector<Vector<ResourceType>> resourceMap = new Vector<>(50);

    public LandSquare getSquare(int x, int y) {
        return this.landSquares.get(x).get(y);
    }

    //=====================随机生成冻土=========================//
    private void initLandformMap_FROZE() {
        for (int i = 0; i < 50; i++) {
            this.landformMap.get(i).set(0, LandformType.FROZENGROUND);
            this.landformMap.get(i).set(1, LandformType.FROZENGROUND);
            this.landformMap.get(i).set(28, LandformType.FROZENGROUND);
            this.landformMap.get(i).set(29, LandformType.FROZENGROUND);

            if (Math.random() > 0.3) {
                this.landformMap.get(i).set(2, LandformType.FROZENGROUND);
                if (Math.random() > 0.5) {
                    this.landformMap.get(i).set(3, LandformType.FROZENGROUND);
                } else {
                    this.landformMap.get(i).set(3, LandformType.GRASSLANDS);
                }
            } else {
                this.landformMap.get(i).set(2, LandformType.GRASSLANDS);
                if (Math.random() > 0.95) {
                    this.landformMap.get(i).set(3, LandformType.FROZENGROUND);
                } else {
                    this.landformMap.get(i).set(3, LandformType.GRASSLANDS);
                }
            }

            if (Math.random() > 0.3) {
                this.landformMap.get(i).set(26, LandformType.FROZENGROUND);
                if (Math.random() > 0.5) {
                    this.landformMap.get(i).set(27, LandformType.FROZENGROUND);
                } else {
                    this.landformMap.get(i).set(27, LandformType.GRASSLANDS);
                }
            } else {
                this.landformMap.get(i).set(26, LandformType.GRASSLANDS);
                if (Math.random() > 0.95) {
                    this.landformMap.get(i).set(27, LandformType.FROZENGROUND);
                } else {
                    this.landformMap.get(i).set(27, LandformType.GRASSLANDS);
                }
            }

            //TODO TRIAL GENERATION
            for (int j = 4; j < 26; j++) {
                this.landformMap.get(i).set(j, LandformType.GRASSLANDS);
            }
        }
    }

    //=====================强行地形/资源=========================//
    private void test_initTerrainResource() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 30; j++) {
                this.terrainMap.get(i).set(j, TerrainType.PLAIN);
                this.resourceMap.get(i).set(j, ResourceType.IRON);
            }
        }
    }

    //=====================初始化LandSquare=========================//
    public void initLandSquare() {
        //TODO new initializing
        this.initLandformMap_FROZE();
        this.test_initTerrainResource();

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 30; j++) {
                this.landSquares.get(i).get(j).
                        initLandSquare(
                        this.terrainMap.get(i).get(j),
                        this.landformMap.get(i).get(j),
                        this.resourceMap.get(i).get(j)
                        );
            }
        }
    }

}