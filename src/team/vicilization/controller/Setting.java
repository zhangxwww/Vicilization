package team.vicilization.controller;

import javax.swing.*;

public class Setting extends State {

    private static javax.swing.JButton confirmButton;

    public Setting(MainWindow mainWindow) {
        super(mainWindow);
        setNextState(StateType.MainGame);

        this.confirmButton = new JButton("Confirm");
        this.confirmButton.setBounds(100, 200, 100, 50);
        this.panel.add(confirmButton);

    }
}
