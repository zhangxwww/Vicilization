package team.vicilization.controller;

import team.vicilization.gamemap.GameMap;
import team.vicilization.mechanics.*;
import team.vicilization.country.*;
import team.vicilization.util.Position;

import javax.swing.*;
import java.awt.*;

public class MainGame extends State {

    public MainGame(MainWindow mainWindow, CountryName[] countrys) {
        super(mainWindow);
        setNextState(StateType.Gameover);

        MapPanel mapPanel = new MapPanel();
        this.panel.add(mapPanel);
    }

    private class MapPanel extends JPanel {
        private Position bias;
        private GameMap map;

        public MapPanel() {
            super();
            this.bias = new Position();
            this.map = new GameMap();
            this.setLayout(null);
            this.setBackground(Color.BLACK);
            this.setBounds(20,50,1240,600);
        }
    }

}
