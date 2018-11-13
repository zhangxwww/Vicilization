package team.vicilization.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStart extends State {

    private static JButton startGameButton;

    public GameStart(MainWindow mainWindow) {
        super(mainWindow);
        this.setNextState(StateType.Setting);

        this.addStartGameButton();
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == GameStart.startGameButton) {
                mainWindow.convertToNextState();
            }
        }
    }

    private void addStartGameButton() {
        this.startGameButton = new JButton("Start");
        this.startGameButton.setBounds(100, 200, 100, 50);
        this.startGameButton.addActionListener(new ButtonListener());
        this.panel.add(startGameButton);
    }

}
