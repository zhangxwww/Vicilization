package team.vicilization.gamemap;

import java.util.Vector;

public class GameMap {
    //先取一列，再取列中每一行
    private Vector<Vector<LandSquare>> landSquares;
    private Vector<Vector<TerrainType>> terrainMap;
    private Vector<Vector<LandformType>> landformMap;
    private Vector<Vector<ResourceType>> resourceMap;

    private Vector<Vector<Double>> temperatureMap;
    private Vector<Vector<Double>> moistureMap;

    //=====================构造函数=========================//
    public GameMap() {
        this.landSquares = new Vector<>(GameMapConfig.MAP_WIDTH);
        this.landformMap = new Vector<>(GameMapConfig.MAP_WIDTH);
        this.terrainMap = new Vector<>(GameMapConfig.MAP_WIDTH);
        this.resourceMap = new Vector<>(GameMapConfig.MAP_WIDTH);
        this.temperatureMap = new Vector<>(GameMapConfig.MAP_WIDTH);
        this.moistureMap = new Vector<>(GameMapConfig.MAP_WIDTH);

        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            this.landSquares.add(new Vector<>(GameMapConfig.MAP_HEIGHT));
            this.landformMap.add(new Vector<>(GameMapConfig.MAP_HEIGHT));
            this.terrainMap.add(new Vector<>(GameMapConfig.MAP_HEIGHT));
            this.resourceMap.add(new Vector<>(GameMapConfig.MAP_HEIGHT));
            this.temperatureMap.add(new Vector<>(GameMapConfig.MAP_HEIGHT));
            this.moistureMap.add(new Vector<>(GameMapConfig.MAP_HEIGHT));
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                this.landSquares.get(i).add(new LandSquare());
                this.landformMap.get(i).add(LandformType.NONE);
                this.terrainMap.get(i).add(TerrainType.PLAIN);
                this.resourceMap.get(i).add(ResourceType.NONE);
                this.temperatureMap.get(i).add(0.0);
                this.moistureMap.get(i).add(0.0);
            }
        }

        this.initTemperature();
        this.initMoisture();
        this.initTerrain_PLAIN_HILL();
        this.initLandform();
        this.initResource();
        this.initTerrain_RIDGE_RIVER_LAKE();

        this.initLandSquare();
    }

    //=====================test=========================//
    public void testPrint() {
        System.out.println("Printing terrainMap");
        for (int i = 0; i < GameMapConfig.MAP_HEIGHT; i++) {
            for (int j = 0; j < GameMapConfig.MAP_WIDTH; j++) {
                System.out.print(this.landSquares.get(j).get(i).getTerrainType());
                System.out.print("\t");
            }
            System.out.print('\n');
        }
        System.out.println("Printing landformMap");
        for (int i = 0; i < GameMapConfig.MAP_HEIGHT; i++) {
            for (int j = 0; j < GameMapConfig.MAP_WIDTH; j++) {
                System.out.print(this.landSquares.get(j).get(i).getLandformType());
                System.out.print("\t");
            }
            System.out.print('\n');
        }
        System.out.println("Printing resourceMap");
        for (int i = 0; i < GameMapConfig.MAP_HEIGHT; i++) {
            for (int j = 0; j < GameMapConfig.MAP_WIDTH; j++) {
                System.out.print(this.landSquares.get(j).get(i).getResourceType());
                System.out.print("\t");
            }
            System.out.print('\n');
        }
    }

    //=====================取某一元素=========================//
    public LandSquare getSquare(int x, int y) {
        return this.landSquares.get(x).get(y);
    }

    //=====================初始化温度分布=======================//
    private void initTemperature() {
        Vector<Integer> temperatureLoc = new Vector<>(GameMapConfig.MAP_WIDTH);
        Vector<Double> temperaturePeak = new Vector<>(GameMapConfig.MAP_WIDTH);
        Vector<Integer> tempLoc = new Vector<>(GameMapConfig.MAP_WIDTH);
        Vector<Double> tempPeak = new Vector<>(GameMapConfig.MAP_WIDTH);
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            temperatureLoc.add(0);
            temperaturePeak.add(0.0);
            tempLoc.add(0);
            tempPeak.add(0.0);
        }

        for(int i = 0;i<GameMapConfig.MAP_WIDTH;i++){
            tempLoc.set(i, (int) ((Math.random() + 1) * 10));
            tempPeak.set(i, (Double) (GameMapConfig.TEMPERATURE - 10 * Math.random()));
        }

        temperatureLoc.set(0, (int) ((tempLoc.get(0) + tempLoc.get(1) + tempLoc.get(2)) / 3));
        temperaturePeak.set(0, (tempPeak.get(0) + tempPeak.get(1) + tempPeak.get(2)) / 3);
        temperatureLoc.set(1, (int) ((tempLoc.get(0) + tempLoc.get(1) + tempLoc.get(2) + tempLoc.get(3)) / 4));
        temperaturePeak.set(1, (tempPeak.get(0) + tempPeak.get(1) + tempPeak.get(2) + tempPeak.get(3)) / 4);
        temperatureLoc.set(GameMapConfig.MAP_WIDTH - 1,
                (int) ((tempLoc.get(GameMapConfig.MAP_WIDTH - 1)
                        + tempLoc.get(GameMapConfig.MAP_WIDTH - 2)
                        + tempLoc.get(GameMapConfig.MAP_WIDTH - 3)) / 3));
        temperaturePeak.set(GameMapConfig.MAP_WIDTH - 1,
                (tempPeak.get(GameMapConfig.MAP_WIDTH - 1)
                        + tempPeak.get(GameMapConfig.MAP_WIDTH - 2)
                        + tempPeak.get(GameMapConfig.MAP_WIDTH - 3)) / 3);
        temperatureLoc.set(GameMapConfig.MAP_WIDTH - 2,
                (int) ((tempLoc.get(GameMapConfig.MAP_WIDTH - 1)
                        + tempLoc.get(GameMapConfig.MAP_WIDTH - 2)
                        + tempLoc.get(GameMapConfig.MAP_WIDTH - 3)
                        + tempLoc.get(GameMapConfig.MAP_WIDTH - 4)) / 4));
        temperaturePeak.set(GameMapConfig.MAP_WIDTH - 2,
                (tempPeak.get(GameMapConfig.MAP_WIDTH - 1)
                        + tempPeak.get(GameMapConfig.MAP_WIDTH - 2)
                        + tempPeak.get(GameMapConfig.MAP_WIDTH - 3)
                        + tempPeak.get(GameMapConfig.MAP_WIDTH - 4)) / 4);
        for (int i = 2; i < GameMapConfig.MAP_WIDTH - 2; i++) {
            temperatureLoc.set(i,
                    (int) ((tempLoc.get(i - 2)
                            + tempLoc.get(i - 1)
                            + tempLoc.get(i)
                            + tempLoc.get(i + 1)
                            + tempLoc.get(i + 2)) / 5));
            temperaturePeak.set(i,
                    (tempPeak.get(i - 2)
                            + tempPeak.get(i - 1)
                            + tempPeak.get(i)
                            + tempPeak.get(i + 1)
                            + tempPeak.get(i + 2)) / 5);
        }

        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                if (j <= temperatureLoc.get(i)) {
                    this.temperatureMap.get(i).set(j,
                            temperaturePeak.get(i)
                                    * j
                                    / temperatureLoc.get(i));
                } else {
                    this.temperatureMap.get(i).set(j,
                            temperaturePeak.get(i)
                                    * (GameMapConfig.MAP_HEIGHT - j)
                                    / temperatureLoc.get(j));
                }
            }
        }
    }

    //=====================初始化降水分布=======================//
    private void initMoisture() {
        int moistureX1 = (int) (Math.random() * GameMapConfig.MAP_WIDTH * 0.5);
        int moistureX2 = (int) ((1 + Math.random()) * GameMapConfig.MAP_WIDTH * 0.5);
        int moistureY1 = (int) (Math.random() * GameMapConfig.MAP_HEIGHT);
        int moistureY2 = (int) (Math.random() * GameMapConfig.MAP_HEIGHT);

        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                this.moistureMap.get(i).set(j,
                        GameMapConfig.MOISTRURE
                                - (Math.abs(i - moistureX1)
                                + Math.abs(j - moistureY1)) / 2.0);
                this.moistureMap.get(i).add(j,
                        GameMapConfig.MOISTRURE
                                - (Math.abs(i - moistureX2)
                                + Math.abs(j - moistureY2)) / 2.0);
            }
        }
    }

    //=====================生成普通地形=========================//
    private void initTerrain_PLAIN_HILL() {
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                if (Math.random() > GameMapConfig.RAND_LEVEL1) {
                    this.terrainMap.get(i).set(j, TerrainType.HILL);
                }
                /*
                else {
                    this.terrainMap.get(i).set(j, TerrainType.HILL);
                }
                */
            }
        }
    }

    //=====================随机生成地貌=========================//
    private void initLandform() {
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                if (this.temperatureMap.get(i).get(j) < GameMapConfig.TEMPERATURE_BOUND_COLD) {
                    this.landformMap.get(i).set(j, LandformType.FROZENGROUND);
                } else if (this.temperatureMap.get(i).get(j) > GameMapConfig.TEMPERATURE_BOUND_HOT) {
                    if (this.moistureMap.get(i).get(j) > GameMapConfig.MOISTURE_BOUND) {
                        this.landformMap.get(i).set(j, LandformType.RAINFOREST);
                    } else {
                        this.landformMap.get(i).set(j, LandformType.DESERT);
                    }
                } else {
                    if (this.moistureMap.get(i).get(j) > GameMapConfig.MOISTURE_BOUND) {
                        this.landformMap.get(i).set(j, LandformType.FOREST);
                    } else {
                        this.landformMap.get(i).set(j, LandformType.GRASSLANDS);
                    }
                }
                if ((Math.random() > GameMapConfig.RAND_LEVEL2)
                        && (this.terrainMap.get(i).get(j) == TerrainType.PLAIN)
                        && (this.landformMap.get(i).get(j) != LandformType.FROZENGROUND)) {
                    this.landformMap.get(i).set(j, LandformType.MARSH);
                }
            }
        }
    }

    //=====================随机生成资源==========================//
    //TODO more resources
    private void initResource() {
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                this.resourceMap.get(1).set(j, ResourceType.NONE);
            }
        }
    }

    //=====================山河湖 抵消其他==========================//
    private void clearForTerr(int x, int y, TerrainType terr) throws IndexOutOfBoundsException {
        if (((terrainMap.get(x).get(y) == TerrainType.RIVER_ROW)
                || (terrainMap.get(x).get(y) == TerrainType.RIVER_COL)
                || (terrainMap.get(x).get(y) == TerrainType.RIVER_NE)
                || (terrainMap.get(x).get(y) == TerrainType.RIVER_NW)
                || (terrainMap.get(x).get(y) == TerrainType.RIVER_SE)
                || (terrainMap.get(x).get(y) == TerrainType.RIVER_SW))
                && (terr != TerrainType.RIDGE)) {
            this.terrainMap.get(x).set(y, TerrainType.LAKE);
        } else {
            this.terrainMap.get(x).set(y, terr);
        }
        this.landformMap.get(x).set(y, LandformType.NONE);
        this.resourceMap.get(x).set(y, ResourceType.NONE);
    }

    private void applyRidge(int serial, int x, int y) throws IndexOutOfBoundsException {
        for (int i = 0; i < GameMapConfig.RIDGE_XY[serial].length; i++) {
            this.terrainMap.get(x + GameMapConfig.RIDGE_XY[serial][i][0])
                    .set(y + GameMapConfig.RIDGE_XY[serial][i][1], TerrainType.RIDGE);
        }
    }

    private void applyRiver(int serial, int x, int y) throws IndexOutOfBoundsException {
        for (int i = 0; i < GameMapConfig.RIVER_XY[serial].length; i++) {
            this.terrainMap.get(x + GameMapConfig.RIVER_XY[serial][i][0])
                    .set(y + GameMapConfig.RIVER_XY[serial][i][1], GameMapConfig.RIVER_KIND[serial][i]);
        }
    }

    private void initTerrain_RIDGE_RIVER_LAKE() {
        int ridgeX, ridgeY;
        for (int cycle = 0; cycle < GameMapConfig.MOUNTAIN_NUM; cycle++) {
            ridgeX = (int) (Math.random() * GameMapConfig.MAP_WIDTH);
            ridgeY = (int) (Math.random() * GameMapConfig.MAP_HEIGHT);

            try {
                int serial = (int) (Math.random() * GameMapConfig.MOUNTAIN_SERIAL);
                int deltaX = GameMapConfig.RIDGE_XY[serial][GameMapConfig.RIDGE_XY[serial].length - 1][0];
                int deltaY = GameMapConfig.RIDGE_XY[serial][GameMapConfig.RIDGE_XY[serial].length - 1][1];

                applyRidge(serial, ridgeX, ridgeY);
                applyRiver((int) (Math.random() * GameMapConfig.RIVER_SERIAL),
                        ridgeX + (int) (Math.random() * deltaX),
                        ridgeY + (int) (Math.random() * deltaY));
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }
    }

    //=====================初始化LandSquare=========================//
    private void initLandSquare() {
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