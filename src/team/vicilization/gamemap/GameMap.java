package team.vicilization.gamemap;

import java.util.Vector;

public class GameMap {
    //先取一列，再取列中每一行
    private Vector<Vector<LandSquare>> landSquares;
    private Vector<Vector<LandformType>> landformMap;
    private Vector<Vector<TerrainType>> terrainMap;
    private Vector<Vector<ResourceType>> resourceMap;

    //=====================构造函数=========================//
    public GameMap() {
        this.landSquares = new Vector<>(GameMapConfig.MAP_WIDTH);
        this.landformMap = new Vector<>(GameMapConfig.MAP_WIDTH);
        this.terrainMap = new Vector<>(GameMapConfig.MAP_WIDTH);
        this.resourceMap = new Vector<>(GameMapConfig.MAP_WIDTH);
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            this.landSquares.set(i, new Vector<>(GameMapConfig.MAP_HEIGHT));
            this.landformMap.set(i, new Vector<>(GameMapConfig.MAP_HEIGHT));
            this.terrainMap.set(i, new Vector<>(GameMapConfig.MAP_HEIGHT));
            this.resourceMap.set(i, new Vector<>(GameMapConfig.MAP_HEIGHT));
        }
        //TODO all the other initializings
    }

    //=====================取某一元素=========================//
    public LandSquare getSquare(int x, int y) {
        return this.landSquares.get(x).get(y);
    }

    //=====================生成普通地形=========================//
    //TODO plain, hill
    private void initTerrainMap_PLAIN_HILL() {

    }

    //=====================随机生成地貌=========================//
    private void initLandformMap_FROZE() {
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            this.landformMap.get(i).set(0, LandformType.FROZENGROUND);
            this.landformMap.get(i).set(GameMapConfig.MAP_HEIGHT - 1, LandformType.FROZENGROUND);

            if (Math.random() > GameMapConfig.RAND_LEVEL1) {
                this.landformMap.get(i).set(1, LandformType.FROZENGROUND);
                if (Math.random() > GameMapConfig.RAND_LEVEL2) {
                    this.landformMap.get(i).set(2, LandformType.FROZENGROUND);
                } else {
                    this.landformMap.get(i).set(2, LandformType.GRASSLANDS);
                }
            } else {
                this.landformMap.get(i).set(1, LandformType.GRASSLANDS);
                if (Math.random() > GameMapConfig.RAND_LEVEL3) {
                    this.landformMap.get(i).set(2, LandformType.FROZENGROUND);
                } else {
                    this.landformMap.get(i).set(2, LandformType.GRASSLANDS);
                }
            }

            if (Math.random() > GameMapConfig.RAND_LEVEL1) {
                this.landformMap.get(i).set(GameMapConfig.MAP_HEIGHT - 3, LandformType.FROZENGROUND);
                if (Math.random() > GameMapConfig.RAND_LEVEL2) {
                    this.landformMap.get(i).set(GameMapConfig.MAP_HEIGHT - 2, LandformType.FROZENGROUND);
                } else {
                    this.landformMap.get(i).set(GameMapConfig.MAP_HEIGHT - 2, LandformType.GRASSLANDS);
                }
            } else {
                this.landformMap.get(i).set(GameMapConfig.MAP_HEIGHT - 3, LandformType.GRASSLANDS);
                if (Math.random() > GameMapConfig.RAND_LEVEL3) {
                    this.landformMap.get(i).set(GameMapConfig.MAP_HEIGHT - 2, LandformType.FROZENGROUND);
                } else {
                    this.landformMap.get(i).set(GameMapConfig.MAP_HEIGHT - 2, LandformType.GRASSLANDS);
                }
            }
            /*
            //TODO TRIAL GENERATION
            for (int j = 3; j < 27; j++) {
                this.landformMap.get(i).set(j, LandformType.GRASSLANDS);
            }
            */
        }
    }

    private void initLandformMap_RAIN_DESERT() {
        int desertX = (int) (Math.random() * GameMapConfig.MAP_WIDTH);

        //TODO double to int
    }

    //=====================随机生成资源==========================//
    //TODO resources
    //=====================山河湖 抵消其他==========================//
    //TODO ridge, river, lake

    //=====================强行地形/资源=========================//
    private void test_initTerrainResource() {
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
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

        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
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