package team.vicilization.controller;

import javax.swing.*;

public class GameStart extends State {

    private javax.swing.JButton startGameButton;

    public GameStart(MainWindow mainWindow) {
        super(mainWindow);
        setNextState(StateType.Setting);

        this.startGameButton = new JButton("Start");
        startGameButton.setBounds(100, 400, 50, 50);
        startGameButton.validate();
        startGameButton.setVisible(true);
        startGameButton.setEnabled(true);
        panel.add(startGameButton);
    }
}
