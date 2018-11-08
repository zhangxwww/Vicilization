package team.vicilization.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStart extends State {

    private static javax.swing.JButton startGameButton;

    public GameStart(MainWindow mainWindow) {
        super(mainWindow);
        setNextState(StateType.Setting);

        this.startGameButton = new JButton("Start");
        this.startGameButton.setBounds(100, 200, 100, 50);
        this.startGameButton.addActionListener(new ButtonClickListener());
        this.panel.add(startGameButton);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == GameStart.startGameButton) {
                mainWindow.convertToNextState();
            }
        }
    }


}
