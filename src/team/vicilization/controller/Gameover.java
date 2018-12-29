package team.vicilization.controller;

import team.vicilization.country.Country;
import team.vicilization.country.CountryName;

import javax.swing.*;
import java.awt.*;
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
        this.restartButton.setBounds(860, 500, 200, 50);
        this.restartButton.addActionListener(new ButtonListener());
        this.restartButton.setFont(new Font("Consolas", Font.BOLD, 25));
        this.panel.add(restartButton);
    }

    private void addVictoryLabel(CountryName name) {
        this.victoryLabel = new JLabel();
        String vectory = name.toString() + " wins";
        this.victoryLabel.setText(vectory);
        this.victoryLabel.setBounds(860, 400, 200, 100);
        this.victoryLabel.setFont(new Font("Consolas", Font.BOLD, 25));
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
