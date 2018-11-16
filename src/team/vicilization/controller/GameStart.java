package team.vicilization.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStart extends State {

    private static JButton startGameButton;
    private JPanel backGroundPanel;
    private JLabel backGroundLabel;

    public GameStart(MainWindow mainWindow) {
        super(mainWindow);
        this.setNextState(StateType.Setting);

        this.addStartGameButton();
        this.setBackgournd();
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == GameStart.startGameButton) {
                mainWindow.convertToNextState();
            }
        }
    }

    private void setBackgournd() {
        this.backGroundPanel = new JPanel() {
            public void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                ImageIcon background = new ImageIcon(
                        "./Resource/Background/start_game_background.jpg");
                graphics.drawImage(background.getImage(), 0, 0,
                        background.getIconWidth(),
                        background.getIconHeight(),
                        this);
            }
        };
        this.backGroundPanel.setBounds(0,0,1920,1080);
        this.backGroundPanel.setLayout(null);
        this.panel.add(this.backGroundPanel);
    }

    private void addStartGameButton() {
        this.startGameButton = new JButton("Start");
        this.startGameButton.setBounds(100, 200, 100, 50);
        this.startGameButton.addActionListener(new ButtonListener());
        this.panel.add(startGameButton);
    }
}
