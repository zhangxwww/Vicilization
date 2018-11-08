package team.vicilization.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.vicilization.country.*;

public class Setting extends State {

    private static JButton confirmButton;
    private static JRadioButton chooseCountryButton_1[];
    private static JRadioButton chooseCountryButton_2[];
    private ButtonGroup chooseCountryButtonGroup_1;
    private ButtonGroup chooseCountryButtonGroup_2;
    private JLabel chooseLeaderLabel;

    private CountryName[] selectedCountryNames;

    public Setting(MainWindow mainWindow) {
        super(mainWindow);
        setNextState(StateType.MainGame);

        this.confirmButton = new JButton("Confirm");
        this.confirmButton.setBounds(100, 400, 100, 50);
        this.confirmButton.addActionListener(new ButtonListener());
        this.panel.add(confirmButton);

        this.chooseLeaderLabel = new JLabel("Choose leader");
        this.chooseLeaderLabel.setBounds(100, 100, 200, 200);
        this.panel.add(chooseLeaderLabel);

        this.chooseCountryButtonGroup_1 = new ButtonGroup();
        this.chooseCountryButtonGroup_2 = new ButtonGroup();
        this.chooseCountryButton_1 = new JRadioButton[CountryName.values().length];
        this.chooseCountryButton_2 = new JRadioButton[CountryName.values().length];

        for (int i = 0; i < CountryName.values().length; i++) {
            String name = CountryName.values()[i].toString();
            this.chooseCountryButton_1[i] = new JRadioButton(name);
            this.chooseCountryButton_1[i].setBounds(200 * i + 200, 300, 100, 50);
            this.chooseCountryButtonGroup_1.add(chooseCountryButton_1[i]);
            this.panel.add(chooseCountryButton_1[i]);
        }

        for (int i = 0; i < CountryName.values().length; i++) {
            String name = CountryName.values()[i].toString();
            this.chooseCountryButton_2[i] = new JRadioButton(name);
            this.chooseCountryButton_2[i].setBounds(200 * i + 200, 400, 100, 50);
            this.chooseCountryButtonGroup_2.add(chooseCountryButton_2[i]);
            this.panel.add(chooseCountryButton_2[i]);
        }

        this.selectedCountryNames = new CountryName[2];
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == Setting.confirmButton) {
                mainWindow.convertToNextState(selectedCountryNames);
            }
            else{
                // TODO with radio button
            }
        }
    }

}
