package team.vicilization.controller;

import team.vicilization.country.Country;
import team.vicilization.country.CountryName;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gameover extends State {

    private static JButton restartButton;
    private JLabel victoryLabel;

    public Gameover(MainWindow mainWindow, Country country) {
        super(mainWindow);
        setNextState(StateType.GameStart);

        this.addRestartButton();
        this.addVictoryLabel(country.getCountryName());
    }

    private void addRestartButton() {
        this.restartButton = new JButton("Restart");
        this.restartButton.setBounds(100, 100, 100, 50);
        this.restartButton.addActionListener(new ButtonListener());
        this.panel.add(restartButton);
    }

    private void addVictoryLabel(CountryName name) {
        this.victoryLabel = new JLabel();
        String vectory = name.toString() + " wins";
        this.victoryLabel.setText(vectory);
        this.victoryLabel.setBounds(30, 30, 100, 100);
        this.panel.add(victoryLabel);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == Gameover.restartButton) {
                mainWindow.convertToNextState();
            }
        }
    }
}
