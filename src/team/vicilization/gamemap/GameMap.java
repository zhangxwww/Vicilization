package team.vicilization.gamemap;

import team.vicilization.util.Position;

import java.util.Vector;

public class GameMap {
    //分别储存地块、地形、地貌、资源组成的地图
    private Vector<Vector<LandSquare>> landSquares;
    private Vector<Vector<TerrainType>> terrainMap;
    private Vector<Vector<LandformType>> landformMap;
    private Vector<Vector<ResourceType>> resourceMap;

    //储存温度和湿度组成的地图
    private Vector<Vector<Double>> temperatureMap;
    private Vector<Vector<Double>> moistureMap;

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
        // 依概率初始化温度和湿度后，先生成基础的平原/丘陵地形
        // 在该基础上，依照温度湿度分布生成地貌
        // 最后生成山峰和河流，该生成会覆盖原有的地形地貌
        // “资源”暂未实现，但接口已经留好
        this.initTemperature();
        this.initMoisture();
        this.initTerrain_PLAIN_HILL();
        this.initLandform();
        this.initResource();
        this.initTerrain_RIDGE_RIVER_LAKE();

        // 最后用已有的地形图、地貌图来初始化每一个单元格
        this.initLandSquare();
    }

    //========================取地块===========================//
    // 可以按照util.Position类的对象来查找，也可以重载为按列数x行数y查找
    public LandSquare getSquare(int x, int y) {
        return this.landSquares.get(x).get(y);
    }

    public LandSquare getSquare(Position position) {
        return this.landSquares.get(position.getX()).get(position.getY());
    }

    //=====================判断位置合法=========================//
    // 与getSquare相同，可按Position或按行列重载数判断位置是否合法
    public boolean isLegalPosition(int x, int y) {
        return ((x >= 0) && (x < GameMapConfig.MAP_WIDTH)
                && (y >= 0) && (y < GameMapConfig.MAP_HEIGHT));
    }

    public boolean isLegalPosition(Position position) {
        return isLegalPosition(position.getX(), position.getY());
    }


    //=====================初始化温度分布=======================//
    // 具体算法为：每一列独立随机生成一个温度峰值的位置
    // 随后对每列的峰值位置做宽度为五列的加权滑动平均，获一定的平滑性
    // 获得各列峰值位置后，各列的温度独立按照规律线性分布
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

        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
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
                            + 2 * tempLoc.get(i - 1)
                            + 4 * tempLoc.get(i)
                            + 2 * tempLoc.get(i + 1)
                            + tempLoc.get(i + 2)) / 10));
            temperaturePeak.set(i,
                    (tempPeak.get(i - 2)
                            + 2 * tempPeak.get(i - 1)
                            + 4 * tempPeak.get(i)
                            + 2 * tempPeak.get(i + 1)
                            + tempPeak.get(i + 2)) / 10);
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
    // 在全球的左1/3和右1/3区域内分别生成一个随机的降水中心
    // 降水中心的降水量在GamemapConfig中有规定
    // 各个单元格的降水则按两处降水中心线性插值
    private void initMoisture() {
        int moistureX1 = (int) (Math.random() * GameMapConfig.MAP_WIDTH * 0.33);
        int moistureX2 = (int) ((2 + Math.random()) * GameMapConfig.MAP_WIDTH * 0.33);
        int moistureY1 = (int) (Math.random() * GameMapConfig.MAP_HEIGHT);
        int moistureY2 = (int) (Math.random() * GameMapConfig.MAP_HEIGHT);


        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                this.moistureMap.get(i).set(j,
                        GameMapConfig.MOISTRURE
                                - (Math.abs(i - moistureX1)
                                + Math.abs(j - moistureY1)) / 2.0);
                this.moistureMap.get(i).set(j,
                        GameMapConfig.MOISTRURE + this.moistureMap.get(i).get(j)
                                - (Math.abs(i - moistureX2)
                                + Math.abs(j - moistureY2)) / 2.0);
            }
        }
    }

    //=====================生成普通地形=========================//
    // 先依概率生成丘陵，其余部分为平原
    private void initTerrain_PLAIN_HILL() {
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                if (Math.random() > GameMapConfig.RAND_LEVEL1) {
                    this.terrainMap.get(i).set(j, TerrainType.HILL);
                }
            }
        }
    }

    //=====================随机生成地貌=========================//
    // 根据温度和湿度的分布不同依概率生成冻土、草原、森林、雨林、沙漠
    private void initLandform() {
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                if (this.temperatureMap.get(i).get(j) < GameMapConfig.TEMPERATURE_BOUND_COLD) {
                    this.landformMap.get(i).set(j, LandformType.FROZENGROUND);
                } else if (this.temperatureMap.get(i).get(j) > GameMapConfig.TEMPERATURE_BOUND_HOT) {
                    if (this.moistureMap.get(i).get(j) > GameMapConfig.MOISTURE_BOUND) {
                        if (Math.random() > GameMapConfig.RAND_LEVEL3) {
                            this.landformMap.get(i).set(j, LandformType.GRASSLANDS);
                        } else {
                            this.landformMap.get(i).set(j, LandformType.RAINFOREST);
                        }
                    } else {
                        this.landformMap.get(i).set(j, LandformType.DESERT);
                    }
                } else {
                    if ((this.moistureMap.get(i).get(j) > GameMapConfig.MOISTURE_BOUND) &&
                            ((this.moistureMap.get(i).get(j) / GameMapConfig.MOISTRURE) * Math.random() * 0.7 * 0.3 + 0.15 > (1 - GameMapConfig.RAND_LEVEL1))) {
                        this.landformMap.get(i).set(j, LandformType.FOREST);
                    } else {
                        if (Math.random() * Math.random() > GameMapConfig.RAND_LEVEL0) {
                            this.landformMap.get(i).set(j, LandformType.FOREST);
                        } else {
                            this.landformMap.get(i).set(j, LandformType.GRASSLANDS);
                        }
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

    //=====================（随机）生成资源==========================//
    // 时间原因目前还没有完成“资源”有关的内容，因此均为NONE
    private void initResource() {
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                this.resourceMap.get(1).set(j, ResourceType.NONE);
            }
        }
    }

    //======================随机生成山河湖==========================//
    // 生成山河湖后覆盖替换其他地形
    // 河流从山发源，河流尽头生成湖泊
    // 山、河流在GamemapConfig中储存了特定的几种形状
    //
    // initTerrain_RIDGE_RIVER_LAKE 负责调用applyRiver, applyRidge
    // clearForTerr 为辅助方法
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
                if (cycle < GameMapConfig.RIVER_NUM) {
                    applyRiver((int) (Math.random() * GameMapConfig.RIVER_SERIAL),
                            ridgeX + (int) (Math.random() * deltaX),
                            ridgeY + (int) (Math.random() * deltaY));
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }
    }

    //=======================初始化LandSquare=========================//
    // 分别根据terrainMap和landformMap及各点位置初始化单元格
    private void initLandSquare() {
        for (int i = 0; i < GameMapConfig.MAP_WIDTH; i++) {
            for (int j = 0; j < GameMapConfig.MAP_HEIGHT; j++) {
                this.landSquares.get(i).get(j).
                        initLandSquare(
                                this.terrainMap.get(i).get(j),
                                this.landformMap.get(i).get(j),
                                this.resourceMap.get(i).get(j),
                                new Position(i, j)

                        );
            }
        }
    }

}