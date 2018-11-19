package team.vicilization.controller;

import team.vicilization.country.Country;
import team.vicilization.country.CountryName;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gameover extends State {

    private static JButton restartButton;
    private JLabel vectoryLabel;

    public Gameover(MainWindow mainWindow, Country country) {
        super(mainWindow);
        setNextState(StateType.GameStart);

        this.addRestartButton();
        this.addVectoryLabel(country.getCountryName());
    }

    private void addRestartButton() {
        this.restartButton = new JButton("Restart");
        this.restartButton.setBounds(100, 100, 100, 50);
        this.restartButton.addActionListener(new ButtonListener());
        this.panel.add(restartButton);
    }

    private void addVectoryLabel(CountryName name) {
        this.vectoryLabel = new JLabel();
        String vectory = name.toString() + " wins";
        this.vectoryLabel.setText(vectory);
        this.vectoryLabel.setBounds(30, 30, 100, 100);
        this.panel.add(vectoryLabel);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == Gameover.restartButton) {
                mainWindow.convertToNextState();
            }
        }
    }
}
