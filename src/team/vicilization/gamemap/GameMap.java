package team.vicilization.gamemap;

import java.util.Vector;

public class GameMap {
    Vector<Vector<LandSquare>> landSquares = new Vector<Vector<LandSquare>>(50);
    Vector<Vector<LandformType>> landformMap = new Vector<Vector<LandformType>>(50);
    Vector<Vector<TerrainType>> terrainMap = new Vector<Vector<TerrainType>>(50);
    Vector<Vector<ResourceType>> resourceMap = new Vector<Vector<ResourceType>>(50);

    public LandSquare getSquare(int row, int column){
        return this.landSquares.get(row).get(column);
    }

    void initLandformMap() {
        for (int i = 0; i < 50; i++) {
            this.landformMap.get(0).set(i, LandformType.FROZENGROUND);
            this.landformMap.get(50).set(i, LandformType.FROZENGROUND);
        }
    }

}