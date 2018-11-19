package team.vicilization.controller;

import javax.swing.*;
import java.awt.*;

import team.vicilization.mechanics.*;
import team.vicilization.country.*;

public class MainWindow extends javax.swing.JFrame {

    private State currentState;
    private JPanel currentPanel;

    public MainWindow() {
        super("MainWindow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.currentState = new GameStart(this);
        this.currentPanel = currentState.getPanel();
        this.add(currentPanel);
    }

    public void convertToNextState(CountryName[] countryNames) {
        this.remove(currentPanel);
        this.repaint();
        this.currentState = new MainGame(this, countryNames);
        this.currentPanel = currentState.getPanel();
        this.add(currentPanel);
        this.revalidate();
    }

    public void convertToNextState(Country country) {
        this.remove(currentPanel);
        this.repaint();
        this.currentState = new Gameover(this, country);
        this.currentPanel = currentState.getPanel();
        this.add(currentPanel);
        this.revalidate();
    }

    public void convertToNextState() {
        this.remove(currentPanel);
        this.repaint();
        switch (currentState.getNextState()) {
            case Setting:
                this.currentState = new Setting(this);
                break;
            case MainGame:
                break;
            case Gameover:
                break;
            case GameStart:
                this.currentState = new GameStart(this);
                break;
            default:
                break;
        }
        this.currentPanel = currentState.getPanel();
        this.add(currentPanel);
        this.revalidate();
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }

}
