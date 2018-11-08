package team.vicilization.controller;

import javax.swing.*;

public class Vicilization extends javax.swing.JFrame {

    private State currentState;

    public Vicilization() {
        super("Vicilization");
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Vicilization vicilization = new Vicilization();
    }

}
