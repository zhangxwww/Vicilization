package team.vicilization.controller;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends javax.swing.JFrame {

    private State currentState;
    private javax.swing.JPanel currentPanel;

    public MainWindow() {
        super("MainWindow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.currentState = new GameStart(this);
        this.currentPanel = currentState.getPanel();
        this.add(currentPanel);

//        this.convertToNextState();
    }

    public void convertToNextState() {
        this.remove(currentPanel);
        this.repaint();
        switch (currentState.getNextState()) {
            case Setting:
                this.currentState = new Setting(this);
                this.currentPanel = currentState.getPanel();
                this.add(currentPanel);
                break;
            case MainGame:
                // TODO
                break;
            default:
                break;
        }
        this.revalidate();
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }

}
