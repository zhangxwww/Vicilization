package team.vicilization.controller;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends javax.swing.JFrame {

    private State currentState;

    public MainWindow() {
        super("MainWindow");
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setVisible(true);

        this.currentState = new GameStart(this);

        this.add(currentState.getPanel());

    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }

}
