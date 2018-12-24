package team.vicilization.controller;


import team.vicilization.gameitem.*;
import team.vicilization.gamemap.*;
import team.vicilization.mechanics.*;
import team.vicilization.country.*;
import team.vicilization.util.Position;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.Timer;
import java.util.Random;


public class MainGame extends State {

    private int round;

    private MapArea mapArea;
    private UpperInfoArea upperInfoArea;
    private LowerInfoArea lowerInfoArea;

    private Vector<Country> countries;
    private Country currentPlayer;
    private Country enermy;

    private Vector<City> cities;
    private Vector<Unit> units;

    private static JButton nextRoundButton;
    private static JButton unitMoveButton;
    private static JButton unitSpecialButton;

    private boolean unitSeleted;
    private boolean unitMoving;
    private Unit seletedUnit;

    public MainGame(MainWindow mainWindow, CountryName[] countrys) {
        super(mainWindow);
        setNextState(StateType.Gameover);

        this.round = 0;
        this.unitSeleted = false;
        this.unitMoving = false;
        this.initMapArea();

        this.initButtons();
        this.initLowerInfoArea();

        this.initCountrys(countrys);
        this.initUpperInfoArea();

        this.cities = new Vector<City>();
    }

    private void nextRound() {
        this.unitSeleted = false;
        this.unitMoving = false;
        this.mapArea.mapPanel.updateMap();
        try {
            this.panel.remove(unitMoveButton);
        } catch (Exception e) {

        }
        this.panel.repaint();
        this.seletedUnit = null;
        this.lowerInfoArea.unshowUnitInfo();

        if (this.currentPlayer.judgeScienceVictory() || round == 3 /* TODO delete later */) {
            this.mainWindow.convertToNextState(currentPlayer);
        } else {
            round++;
            this.currentPlayer.endOfCurrentRound();
            this.enermy = this.currentPlayer;
            this.currentPlayer = this.countries
                    .elementAt(round % 2);
            this.currentPlayer.readyForNewRound();
            this.upperInfoArea.update();
        }
        // TODO
    }

    private void initCountrys(CountryName[] countrys) {
        this.countries = new Vector<Country>(2);
        this.units = new Vector<Unit>();
        for (int i = 0; i < 2; i++) {
            Country country = new Country(countrys[i]);
            this.countries.add(country);
            this.initUnitsForOneCountry(country, i);
        }
        this.currentPlayer = this.countries.elementAt(0);
        this.enermy = this.countries.elementAt(1);
    }

    private void initUnitsForOneCountry(Country country, int order) {
        Random initPosition = new Random();
        while (true) {
            int xbias = order * GameMapConfig.MAP_WIDTH / 2;
            int ybias = order * GameMapConfig.MAP_HEIGHT / 2;
            int x = initPosition.nextInt(GameMapConfig.MAP_WIDTH / 2) + xbias;
            int y = initPosition.nextInt(GameMapConfig.MAP_HEIGHT / 2) + ybias;
            LandSquare landSquare = mapArea.at(x, y);
            if ((landSquare.getTerrainType() == TerrainType.PLAIN
                    || landSquare.getTerrainType() == TerrainType.HILL)
                    && (landSquare.getLandformType() == LandformType.GRASSLANDS
                    || landSquare.getLandformType() == LandformType.RAINFOREST
                    || landSquare.getLandformType() == LandformType.FOREST)
                    && (landSquare.getResourceType() == ResourceType.NONE)) {

                Position initPos = new Position(x, y);
                Unit explorer = new Explorer(initPos, country);
                country.addNewUnit(explorer);
                this.units.add(explorer);
                this.mapArea.addUnitInMap(explorer, initPos);
                break;
            }
        }
    }

    private void initButtons() {
        GameButtonsListener listener = new GameButtonsListener();

        this.nextRoundButton = new JButton("Next Round");
        this.nextRoundButton.setBounds(1060, 600, 200, 100);
        this.nextRoundButton.addActionListener(listener);
        this.panel.add(nextRoundButton);

        this.unitMoveButton = new JButton("Move / Fight");
        this.unitMoveButton.setBounds(20, 600, 200, 50);
        this.unitMoveButton.addActionListener(listener);

        this.unitSpecialButton = new JButton();
        this.unitSpecialButton.setBounds(20, 650, 200, 50);
        this.unitSpecialButton.addActionListener(listener);
    }

    private void initUpperInfoArea() {
        this.upperInfoArea = new UpperInfoArea();
        this.panel.add(upperInfoArea);
    }

    private void initLowerInfoArea() {
        this.lowerInfoArea = new LowerInfoArea();
        this.panel.add(lowerInfoArea);
    }

    private void selectUnit(Unit unit) {
        this.unitSeleted = true;
        this.seletedUnit = unit;
        this.lowerInfoArea.showUnitInfo(unit);
        if (!seletedUnit.isMovedThisTurn()) { // 没有移动，显示移动按钮
            this.panel.add(unitMoveButton);
        }
        if (seletedUnit.getSubType() == UnitSubType.EXPLORER) {
            this.panel.add(unitSpecialButton);
            // !!! 如果这里要换成icon的话，在GameButtonLisioner 那里也要做同样的修改 !!!
            unitSpecialButton.setText("Build city");
        }
        this.panel.repaint();
    }

    private void unselectUnit() {
        this.unitSeleted = false;
        this.unitMoving = false;
        try {
            this.panel.remove(unitMoveButton);
        } catch (Exception e) {
        }
        try {
            this.panel.remove(unitSpecialButton);
        } catch (Exception e) {
        }
        this.panel.repaint();
        this.seletedUnit = null;
        this.lowerInfoArea.unshowUnitInfo();
    }

    private void buildCity() {
        Position pos = seletedUnit.getPosition();
        currentPlayer.deleteUnit(seletedUnit);
        City city = currentPlayer.buildNewCity(pos, mapArea.getMap(), enermy);
        this.cities.add(city);
        units.remove(seletedUnit);
        unselectUnit();
        this.mapArea.mapPanel.updateMap();
    }

    private void selectCity(City city) {
        // TODO
    }

    private void selectScience(ScienceName scienceName) {
        // TODO
    }

    private void showScienceInfo(ScienceName scienceName) {
        // TODO
    }

    private void showScienceTree() {
        // TODO
    }


    private void changePlayer() {
        // TODO
    }

    private void fight(Fightable fighter, Fightable fought) {
        // TODO
    }

    private void fight(Fightable fighter, City city) {
        // TODO
    }

    private void showGiant() {
        // TODO
    }

    private void recruiteGiant(GiantName giant) {
        // TODO
    }

    private void showAllowdBuildings() {
        // TODO
    }

    private void showAllowedUnits() {
        // TODO
    }

    private void selectProduction() {
        // TODO
    }

    private void startTradeRoute() {
        // TODO
    }

    private void selectTradeCity() {
        // TODO
    }

    private void initMapArea() {
        this.mapArea = new MapArea();
        this.panel.add(mapArea);
    }

    private class GameButtonsListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == MainGame.nextRoundButton) {
                nextRound();
            } else if (event.getSource() == MainGame.unitMoveButton) {
                mapArea.drawAccessableSquares();
                unitMoving = true;
            } else if (event.getSource() == MainGame.unitSpecialButton) {
                if (MainGame.unitSpecialButton.getText() == "Build city") {
                    buildCity();
                }
            }
            // TODO finish with other actions
        }
    }

    private class UpperInfoArea extends JPanel {
        // show flow value, resource count ... here
        private JLabel sciencePointInfo;
        private JLabel moneyInfo;
        private JLabel roundInfo;

        private JLabel sciencePointSymbolLabel;
        private JLabel moneySymbolLabel;
        private JLabel roundSymbolLabel;

        private ImageIcon scienceIcon;
        private ImageIcon moneyIcon;

        public UpperInfoArea() {
            // TODO science info
            super();
            this.setLayout(null);
            this.setBounds(20, 0, 1240, 50);
            this.initIcons();
            this.initLabels();
            this.update();
        }

        public void update() {
            this.sciencePointInfo.setText(
                    String.valueOf(currentPlayer.getFlowValue().getScience()));
            this.moneyInfo.setText(
                    String.valueOf(currentPlayer.getStockValue().getMoney()) + "(+"
                            + String.valueOf(currentPlayer.getFlowValue().getMoney()) + ")");

            this.roundInfo.setText(String.valueOf(round));
        }

        private void initLabels() {
            this.sciencePointInfo = new JLabel();
            this.moneyInfo = new JLabel();
            this.roundInfo = new JLabel();

            this.sciencePointInfo.setBounds(60, 0, 80, 50);
            this.moneyInfo.setBounds(210, 0, 80, 50);
            this.roundInfo.setBounds(1060, 0, 20, 25);

            this.sciencePointInfo.setFont(new Font("Consolas", Font.PLAIN, 22));
            this.moneyInfo.setFont(new Font("Consolas", Font.PLAIN, 22));

            this.sciencePointSymbolLabel = new JLabel(scienceIcon);
            this.moneySymbolLabel = new JLabel(moneyIcon);
            this.roundSymbolLabel = new JLabel("R");

            this.sciencePointSymbolLabel.setOpaque(true);
            this.moneySymbolLabel.setOpaque(true);

            this.sciencePointSymbolLabel.setBounds(0, 0, 50, 50);
            this.moneySymbolLabel.setBounds(150, 0, 50, 50);
            this.roundSymbolLabel.setBounds(1040, 0, 20, 25);

            this.add(sciencePointSymbolLabel);
            this.add(moneySymbolLabel);
            this.add(roundSymbolLabel);
            this.add(sciencePointInfo);
            this.add(moneyInfo);
            this.add(roundInfo);
        }

        private void initIcons() {
            this.scienceIcon = new ImageIcon("./Resource/info/science.png");
            this.moneyIcon = new ImageIcon("./Resource/info/money.png");
        }
    }

    private class ProductionArea extends JPanel {
        // TODO shown when choosing what to produce
        public ProductionArea(City city) {
            super();
            // TODO
        }
    }

    private class ScienceArea extends JPanel {
        // TODO show and select the sciences here
        public ScienceArea(ScienceName scienceName) {
            super();
            // TODO
        }
    }

    private class LowerInfoArea extends JPanel {
        // show selected unit / city info
        private JLabel unitTypeInfo;
        private JLabel unitHealthInfo;
        private JLabel unitAttackInfo;
        private JLabel unitDefenceInfo;
        private JLabel unitMovabilityInfo;

        private JLabel healthLabel;
        private JLabel attackLabel;
        private JLabel defenceLabel;
        private JLabel movabilityLabel;

        private ImageIcon healthIcon;
        private ImageIcon attackIcon;
        private ImageIcon defenceIcon;
        private ImageIcon movabilityIcon;
        // TODO show city info

        public LowerInfoArea() {
            super();
            this.setBounds(250, 600, 600, 100);
            this.setLayout(null);
            this.initIcons();
            this.initLabels();
        }

        public void showUnitInfo(Unit unit) {
            this.unitTypeInfo.setText(unit.getSubType().toString());
            this.unitAttackInfo.setText(String.valueOf(unit.getUnitInfo().getAttack()));
            this.unitDefenceInfo.setText(String.valueOf(unit.getUnitInfo().getDefence()));
            this.unitHealthInfo.setText(String.valueOf(unit.getHealth()));
            this.unitMovabilityInfo.setText(String.valueOf(unit.getMobility()));
        }

        public void unshowUnitInfo() {
            this.unitTypeInfo.setText("");
            this.unitAttackInfo.setText("");
            this.unitDefenceInfo.setText("");
            this.unitHealthInfo.setText("");
            this.unitMovabilityInfo.setText("");
        }

        private void initIcons() {
            this.healthIcon = new ImageIcon("./Resource/unitinfo/health.png");
            this.attackIcon = new ImageIcon("./Resource/unitinfo/attack.png");
            this.defenceIcon = new ImageIcon("./Resource/unitinfo/defence.png");
            this.movabilityIcon = new ImageIcon("./Resource/unitinfo/movability.png");
        }

        private void initLabels() {
            this.attackLabel = new JLabel(attackIcon);
            this.defenceLabel = new JLabel(defenceIcon);
            this.healthLabel = new JLabel(healthIcon);
            this.movabilityLabel = new JLabel(movabilityIcon);

            this.attackLabel.setBounds(0, 20, 40, 40);
            this.defenceLabel.setBounds(0, 60, 40, 40);
            this.healthLabel.setBounds(110, 20, 40, 40);
            this.movabilityLabel.setBounds(110, 60, 40, 40);

            this.unitTypeInfo = new JLabel();
            this.unitHealthInfo = new JLabel();
            this.unitAttackInfo = new JLabel();
            this.unitDefenceInfo = new JLabel();
            this.unitMovabilityInfo = new JLabel();

            this.unitTypeInfo.setBounds(50, 0, 120, 20);
            this.unitAttackInfo.setBounds(50, 20, 60, 40);
            this.unitDefenceInfo.setBounds(50, 60, 60, 40);
            this.unitHealthInfo.setBounds(160, 20, 60, 40);
            this.unitMovabilityInfo.setBounds(160, 60, 60, 40);

            this.unitTypeInfo.setFont(new Font("Consolas", Font.BOLD, 20));
            this.unitAttackInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.unitDefenceInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.unitHealthInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.unitMovabilityInfo.setFont(new Font("Consolas", Font.PLAIN, 18));

            this.add(attackLabel);
            this.add(defenceLabel);
            this.add(healthLabel);
            this.add(movabilityLabel);
            this.add(unitTypeInfo);
            this.add(unitHealthInfo);
            this.add(unitAttackInfo);
            this.add(unitDefenceInfo);
            this.add(unitMovabilityInfo);
        }
    }


    private class MapArea extends JPanel {

        private MapPanel mapPanel;

        public MapArea() {
            super();

            this.setBounds(20, 50, 1240, 550);
            this.setBackground(Color.BLUE);
            this.setLayout(null);

            mapPanel = new MapPanel();
            this.add(mapPanel);
        }

        public void addUnitInMap(Unit unit, Position position) {
            this.mapPanel.addUnit(unit, position);
        }

        public LandSquare at(int x, int y) {
            return mapPanel.map.getSquare(x, y);
        }

        public void drawAccessableSquares() {
            this.mapPanel.drawAccessableSquares();
        }

        public GameMap getMap() {
            return this.mapPanel.map;
        }

        private class MapPanel extends JPanel {
            private Position bias;
            private GameMap map;

            private ImageIcon hill_desert_icon;
            private ImageIcon hill_frozenground_icon;
            private ImageIcon hills_forest_icon;
            private ImageIcon hills_grasslands_icon;
            private ImageIcon hills_rainforest_icon;
            private ImageIcon lake_icon;
            private ImageIcon plain_desert_icon;
            private ImageIcon plain_forest_icon;
            private ImageIcon plain_frozenground_icon;
            private ImageIcon plain_grasslands_icon;
            private ImageIcon plain_marsh_icon;
            private ImageIcon plain_rainforest_icon;
            private ImageIcon ridge_icon;
            private ImageIcon river_col_icon;
            private ImageIcon river_ne_icon;
            private ImageIcon river_nw_icon;
            private ImageIcon river_row_icon;
            private ImageIcon river_se_icon;
            private ImageIcon river_sw_icon;

            private ImageIcon constructor_icon;
            private ImageIcon explorer_icon;
            private ImageIcon footman_icon;

            Vector<LandSquare> accessableSquares;

            public MapPanel() {
                super();
                this.map = new GameMap();

                this.setLayout(null);

                this.initIcons();

                // TODO this will change later
                this.setBounds(0, 0,
                        GameMapConfig.MAP_WIDTH * 50,
                        GameMapConfig.MAP_HEIGHT * 50);

                MapMouseEventListener dragScreen = new MapMouseEventListener();
                this.addMouseListener(dragScreen);
                this.addMouseMotionListener(dragScreen);

                this.initSquares();
                this.drawMapWithoutUnits();
                this.repaint();
            }

            private void initIcons() {
                this.hill_desert_icon = new ImageIcon("./Resource/terrain/hill_desert.png");
                this.hill_frozenground_icon = new ImageIcon("./Resource/terrain/hill_frozenground.png");
                this.hills_forest_icon = new ImageIcon("./Resource/terrain/hills_forest.png");
                this.hills_grasslands_icon = new ImageIcon("./Resource/terrain/hills_grasslands.png");
                this.hills_rainforest_icon = new ImageIcon("./Resource/terrain/hills_rainforest.png");
                this.lake_icon = new ImageIcon("./Resource/terrain/lake.png");
                this.plain_desert_icon = new ImageIcon("./Resource/terrain/plain_desert.png");
                this.plain_forest_icon = new ImageIcon("./Resource/terrain/plain_forest.png");
                this.plain_frozenground_icon = new ImageIcon("./Resource/terrain/plain_frozenground.png");
                this.plain_grasslands_icon = new ImageIcon("./Resource/terrain/plain_grasslands.png");
                this.plain_marsh_icon = new ImageIcon("./Resource/terrain/plain_marsh.png");
                this.plain_rainforest_icon = new ImageIcon("./Resource/terrain/plain_rainforest.png");
                this.ridge_icon = new ImageIcon("./Resource/terrain/ridge.png");
                this.river_col_icon = new ImageIcon("./Resource/terrain/river_col.png");
                this.river_ne_icon = new ImageIcon("./Resource/terrain/river_ne.png");
                this.river_nw_icon = new ImageIcon("./Resource/terrain/river_nw.png");
                this.river_row_icon = new ImageIcon("./Resource/terrain/river_row.png");
                this.river_se_icon = new ImageIcon("./Resource/terrain/river_se.png");
                this.river_sw_icon = new ImageIcon("./Resource/terrain/river_sw.png");

                this.constructor_icon = new ImageIcon("./Resource/unit/constructor.png");
                this.explorer_icon = new ImageIcon("./Resource/unit/explorer.png");
                this.footman_icon = new ImageIcon("./Resource/unit/footman.png");
            }

            private void initSquares() {
                for (int i = 0; i < GameMapConfig.MAP_HEIGHT; i++) {
                    for (int j = 0; j < GameMapConfig.MAP_WIDTH; j++) {
                        JLabel square = new JLabel();
                        square.setOpaque(true);
                        square.setBounds(
                                j * 50, i * 50,
                                50, 50);
                        this.add(square);
                    }
                }
            }

            public void drawAccessableSquares() {
                accessableSquares = seletedUnit.getAvailableLocation(map);
                for (LandSquare landSquare : accessableSquares) {
                    Position position = landSquare.getPosition();
                    JLabel square = (JLabel) getComponentAt(
                            position.getX() * 50, position.getY() * 50);
                    square.setIcon(null);
                    square.setBackground(Color.GREEN);
                }
                this.repaint();
            }

            private void drawMapWithoutUnits() {
                for (int i = 0; i < GameMapConfig.MAP_HEIGHT; i++) {
                    for (int j = 0; j < GameMapConfig.MAP_WIDTH; j++) {
                        JLabel square = (JLabel) getComponentAt(
                                j * 50, i * 50);
                        square.setBackground(Color.WHITE);
                        switch (map.getSquare(j, i).getTerrainType()) {
                            case HILL:
                                switch (map.getSquare(j, i).getLandformType()) {
                                    case DESERT:
                                        square.setIcon(hill_desert_icon);
                                        break;
                                    case FROZENGROUND:
                                        square.setIcon(hill_frozenground_icon);
                                        break;
                                    case FOREST:
                                        square.setIcon(hills_forest_icon);
                                        break;
                                    case GRASSLANDS:
                                        square.setIcon(hills_grasslands_icon);
                                        break;
                                    case RAINFOREST:
                                        square.setIcon(hills_rainforest_icon);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case LAKE:
                                square.setIcon(lake_icon);
                                break;
                            case PLAIN:
                                switch (map.getSquare(j, i).getLandformType()) {
                                    case DESERT:
                                        square.setIcon(plain_desert_icon);
                                        break;
                                    case FOREST:
                                        square.setIcon(plain_forest_icon);
                                        break;
                                    case FROZENGROUND:
                                        square.setIcon(plain_frozenground_icon);
                                        break;
                                    case GRASSLANDS:
                                        square.setIcon(plain_grasslands_icon);
                                        break;
                                    case MARSH:
                                        square.setIcon(plain_marsh_icon);
                                        break;
                                    case RAINFOREST:
                                        square.setIcon(plain_rainforest_icon);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case RIDGE:
                                square.setIcon(ridge_icon);
                                break;
                            case RIVER_COL:
                                square.setIcon(river_col_icon);
                                break;
                            case RIVER_NE:
                                square.setIcon(river_ne_icon);
                                break;
                            case RIVER_NW:
                                square.setIcon(river_nw_icon);
                                break;
                            case RIVER_ROW:
                                square.setIcon(river_row_icon);
                                break;
                            case RIVER_SE:
                                square.setIcon(river_se_icon);
                                break;
                            case RIVER_SW:
                                square.setIcon(river_sw_icon);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

            public void updateMap() {
                drawMapWithoutUnits();
                for (City city : cities) {
                    Position position = city.getLocation();
                    JLabel square = (JLabel) getComponentAt(
                            position.getX() * 50, position.getY() * 50);
                    square.setIcon(null);
                    square.setBackground(Color.RED);
                }
                for (Unit unit : units) {
                    Position position = unit.getPosition();
                    JLabel square = (JLabel) getComponentAt(
                            position.getX() * 50, position.getY() * 50);
                    switch (unit.getSubType()) {
                        case EXPLORER:
                            square.setIcon(explorer_icon);
                            break;
                        case CONSTRUCTOR:
                            square.setIcon(constructor_icon);
                            break;
                        case FOOTMAN:
                            square.setIcon(footman_icon);
                            break;
                        // TODO add other
                        default:
                            break;
                    }
                    square.setBackground(CountryConfig.COLOR_OF_COUNTRY
                            .get(unit.getCountry().getCountryName()));
                }
                this.repaint();
            }

            private void addUnit(Unit unit, Position position) {
                JLabel square = (JLabel) getComponentAt(
                        position.getX() * 50, position.getY() * 50);
                switch (unit.getSubType()) {
                    case EXPLORER:
                        square.setIcon(explorer_icon);
                        break;
                    case CONSTRUCTOR:
                        square.setIcon(constructor_icon);
                        break;
                    case FOOTMAN:
                        square.setIcon(footman_icon);
                        break;
                    // TODO add other
                    default:
                        break;
                }
                square.setBackground(CountryConfig.COLOR_OF_COUNTRY
                        .get(unit.getCountry().getCountryName()));
            }

            private class MapMouseEventListener implements MouseInputListener {

                private boolean moving = false;
                private int xinit = 0;
                private int yinit = 0;
                private int xcur = 0;
                private int ycur = 0;
                Timer timer;

                @Override
                public void mouseClicked(MouseEvent event) {
                    JLabel square = (JLabel) getComponentAt(event.getX(), event.getY());

                    int posx = event.getX() / 50;
                    int posy = event.getY() / 50;
                    if (!unitMoving) { // 单位没有准备移动
                        for (Unit u : units) {
                            if (u.getPosition().equals(new Position(posx, posy))) {
                                if (u.getCountry() == currentPlayer) {
                                    selectUnit(u);
                                }
                            }
                        }
                        // TODO select city
                    } else { // 单位移动
                        Position position = new Position(posx, posy);
                        LandSquare landSquare = map.getSquare(posx, posy);
                        if (accessableSquares.contains(landSquare)) {
                            seletedUnit.moveTo(position);
                        }
                        unselectUnit();
                    }
                    updateMap();
                }

                @Override
                public void mouseEntered(MouseEvent event) {
                }

                @Override
                public void mouseExited(MouseEvent event) {
                }

                @Override
                public void mousePressed(MouseEvent event) {
                    xinit = event.getX();
                    yinit = event.getY();
                    timer = new Timer(20, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mapPanel.setLocation(
                                    xcur - xinit + mapPanel.getX(),
                                    ycur - yinit + mapPanel.getY());
                        }
                    });
                }

                @Override
                public void mouseReleased(MouseEvent event) {
                    if (moving) {
                        int x1 = event.getX();
                        int y1 = event.getY();
                        mapPanel.setLocation(
                                xcur - xinit + mapPanel.getX(),
                                ycur - yinit + mapPanel.getY());
                        moving = false;
                        timer.stop();
                    }
                }

                @Override
                public void mouseDragged(MouseEvent event) {
                    xcur = event.getX();
                    ycur = event.getY();
                    moving = true;
                    timer.start();
                }

                @Override
                public void mouseMoved(MouseEvent event) {
                }
            }
        }
    }
}
