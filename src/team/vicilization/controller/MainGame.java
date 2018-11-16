package team.vicilization.controller;

import team.vicilization.gameitem.City;
import team.vicilization.gameitem.Fightable;
import team.vicilization.gameitem.Producable;
import team.vicilization.gameitem.Unit;
import team.vicilization.gamemap.GameMap;
import team.vicilization.mechanics.*;
import team.vicilization.country.*;
import team.vicilization.util.Position;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.security.PublicKey;
import java.util.Vector;
import javax.swing.Timer;

import static team.vicilization.country.CountryName.*;

public class MainGame extends State {

    private int round;
    private MapArea mapArea;
    private Vector<Country> countries;
    private Country currentPlayer;

    public MainGame(MainWindow mainWindow, CountryName[] countrys) {
        super(mainWindow);
        setNextState(StateType.Gameover);

        this.round = 0;
        this.initMapArea();
        this.initCountrys(countrys);
    }

    private void initUnitsForOneCountry(Country country) {
        // TODO
    }

    private void selectUnit(Unit unit) {
        // TODO
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


    private class DropdownButtonList extends JPanel {
        // TODO show dropdown buttons when units or cities being selected
        public DropdownButtonList(Unit unit) {
            super();
            // TODO
        }

        public DropdownButtonList(City city) {
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
            this.setBounds(20, 50, 1240, 600);
            this.setBackground(Color.BLUE);
            this.setLayout(null);

            mapPanel = new MapPanel();
            this.add(mapPanel);
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

                DragScreen dragScreen = new DragScreen();
                this.addMouseListener(dragScreen);
                this.addMouseMotionListener(dragScreen);

                this.addMap();
            }

            private void addMap() {
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

            private class DragScreen implements MouseInputListener {
                private boolean moving = false;
                private int xinit = 0;
                private int yinit = 0;
                private int xcur = 0;
                private int ycur = 0;
                Timer timer;

                @Override
                public void mouseClicked(MouseEvent event) {
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
