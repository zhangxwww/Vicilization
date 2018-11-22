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

    private Vector<Country> countries;
    private Country currentPlayer;

    private Vector<City> cities;
    private Vector<Unit> units;

    private static JButton nextRoundButton;

    public MainGame(MainWindow mainWindow, CountryName[] countrys) {
        super(mainWindow);
        setNextState(StateType.Gameover);

        this.round = 0;
        this.initMapArea();
        this.initCountrys(countrys);

        this.initButtons();
        this.initUpperInfoArea();

        this.cities = new Vector<City>();
    }

    private void nextRound() {
        if (this.currentPlayer.judgeVectory() || round == 3 /* TODO delete later */) {
            this.mainWindow.convertToNextState(currentPlayer);
        } else {
            round++;
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
            this.initUnitsForOneCountry(country);
        }
        this.currentPlayer = this.countries.elementAt(0);
    }

    private void initUnitsForOneCountry(Country country) {
        Random initPosition = new Random();
        while (true) {
            int x = initPosition.nextInt(40);
            int y = initPosition.nextInt(30);
            // TODO add following later
//            LandSquare landSquare = mapArea.at(x, y);
            if (true/*(landSquare.getTerrainType() == TerrainType.PLAIN
                    || landSquare.getTerrainType() == TerrainType.HILL)
                    && (landSquare.getLandformType() == LandformType.GRASSLANDS)
                    && (landSquare.getResourceType() == ResourceType.NONE)*/) {

                Position initPos = new Position(x, y);
                Unit explorer = new Explorer(initPos);
                country.addNewUnit(explorer, initPos);
                this.units.add(explorer);
                this.mapArea.addUnitInMap(explorer, initPos);
                break;
            }
        }

    }

    private void initMapArea() {
        this.mapArea = new MapArea();
        this.panel.add(mapArea);
    }

    private void initButtons() {
        this.nextRoundButton = new JButton("Next Round");
        this.nextRoundButton.setBounds(1060, 600, 200, 100);
        this.nextRoundButton.addActionListener(new GameButtonsListener());
        this.panel.add(nextRoundButton);
    }

    private void initUpperInfoArea() {
        this.upperInfoArea = new UpperInfoArea();
        this.panel.add(upperInfoArea);
    }

    private void selectUnit(Unit unit) {
        // TODO
        System.out.println("select");
    }

    private void selectCity(City city) {
        // TODO
    }

    private void selectScience(Science science) {
        // TODO
    }

    private void showCityInfo(City city) {
        // TODO
    }

    private void unshowCityInfo() {
        // TODO
    }

    private void showUnitInfo(Unit unit) {
        // TODO
    }

    private void unshowUnitInfo() {
        // TODO
    }

    private void showScienceInfo(Science science) {
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

    private void selectProduction(Producable production) {
        // TODO
    }

    private void startTradeRoute() {
        // TODO
    }

    private void selectTradeCity() {
        // TODO
    }


    private void nextRound() {
        if (this.currentPlayer.judgeVectory()) {
            // TODO convert to next state
        } else {
            round++;
            this.currentPlayer = this.countries
                    .elementAt(round % 2);
            this.currentPlayer.readyForNewRound();
            // TODO update upper info area
        }
        // TODO
    }

    private void initCountrys(CountryName[] countrys) {
        this.countries = new Vector<Country>(2);
        for (int i = 0; i < 2; i++) {
            Country country = new Country(countrys[i]);
            this.countries.add(country);
            this.initUnitsForOneCountry(country);
        }
    }

    private void initMapArea() {
        this.mapArea = new MapArea();
        this.panel.add(mapArea);
    }

    private class UpperInfoArea extends JPanel {
        // TODO show flow value, resource count ... here
        public UpperInfoArea() {
            super();
            // TODO
        }

        public void update(Country country) {
            // TODO
            
    private class GameButtonsListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == MainGame.nextRoundButton) {
                nextRound();
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

        public UpperInfoArea() {
            super();
            this.sciencePointInfo = new JLabel();
            this.moneyInfo = new JLabel();
            this.roundInfo = new JLabel();

            this.sciencePointInfo.setBounds(15, 0, 40, 25);
            this.moneyInfo.setBounds(15, 25, 40, 25);
            this.roundInfo.setBounds(1060, 0, 20, 25);

            this.sciencePointSymbolLabel = new JLabel("S");
            this.moneySymbolLabel = new JLabel("M");
            this.roundSymbolLabel = new JLabel("R");

            this.sciencePointSymbolLabel.setBounds(0, 0, 15, 25);
            this.moneySymbolLabel.setBounds(0, 25, 15, 25);
            this.roundSymbolLabel.setBounds(1040, 0, 20, 25);

            this.update();

            this.setLayout(null);

            this.add(sciencePointSymbolLabel);
            this.add(moneySymbolLabel);
            this.add(roundSymbolLabel);
            this.add(sciencePointInfo);
            this.add(moneyInfo);
            this.add(roundInfo);

            // TODO show resources

            this.setBounds(20, 0, 1240, 50);
        }

        public void update() {
            // TODO this should be rewritten later
            this.sciencePointInfo.setText("3.4");
            this.moneyInfo.setText("0/6");
            this.roundInfo.setText(String.valueOf(round));
            // TODO show resources
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
        public ScienceArea(Science science) {
            super();
            // TODO
        }
    }


    private class SelectedButtonList extends JPanel {
        // TODO show buttons when units or cities being selected
        public SelectedButtonList(Unit unit) {
            super();
            // TODO
        }


        public SelectedButtonList(City city) {
            super();
            // TODO
        }
    }

    private class InfoArea extends JPanel {
        // TODO used to show info of selected unit or city
        public InfoArea(Unit unit) {
            super();
            // TODO
        }

        public InfoArea(City city) {
            super();
            // TODO
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
            return mapPanel.map.getSquare(y, x);
        }

        private class MapPanel extends JPanel {
            private Position bias;
            private GameMap map;

            public MapPanel() {
                super();
                this.map = new GameMap();

                this.setLayout(null);

                // TODO this will change later
                this.setBounds(0, 0,
                        40 * 50, 30 * 50);

                MapMouseEventListener dragScreen = new MapMouseEventListener();
                this.addMouseListener(dragScreen);
                this.addMouseMotionListener(dragScreen);

                this.drawMapWithoutUnits();
            }

            private void drawMapWithoutUnits() {
                // TODO this will be rewriten later
                for (int i = 0; i < 40; i++) {
                    for (int j = 0; j < 50; j++) {
                        JLabel square = new JLabel();
                        square.setOpaque(true);
                        if ((i + j) % 2 == 0) {
                            square.setBackground(Color.BLACK);
                        } else {
                            square.setBackground(Color.WHITE);
                        }
                        square.setText("" + i + "" + j);
                        square.setBounds(
                                j * 50, i * 50,
                                50, 50);
                        this.add(square);
                    }
                }
            }

            public void drawUnits() {
                // TODO
            }


            private void addUnit(Unit unit, Position position) {
                // TODO rewrite later
                JLabel square = (JLabel) getComponentAt(
                        position.getX() * 50, position.getY() * 50);
                square.setBackground(Color.ORANGE);
                square.setText("unit");
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
                    if (square.getText() == "unit") {
                        for (Unit u : units) {
                            if (u.getPosition().getX() == event.getX() / 50
                                    && u.getPosition().getY() == event.getY() / 50) {

                                selectUnit(u);
                                break;
                            }
                        }
                    }
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
