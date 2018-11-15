package team.vicilization.controller;

import team.vicilization.gamemap.GameMap;
import team.vicilization.mechanics.*;
import team.vicilization.country.*;
import team.vicilization.util.Position;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.Timer;

import static team.vicilization.country.CountryName.*;

public class MainGame extends State {

    private MapArea mapArea;
    private Vector<Country> countries;
    private Country currentPlayer;

    public MainGame(MainWindow mainWindow, CountryName[] countrys) {
        super(mainWindow);
        setNextState(StateType.Gameover);

        this.initMapArea();
        this.initCountrys(countrys);
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

    private void initUnitsForOneCountry(Country country) {
        // TODO
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
