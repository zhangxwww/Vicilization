package team.vicilization.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import team.vicilization.country.*;

public class Setting extends State {

    private static JButton confirmButton;
    private static Vector<JRadioButton> chooseCountryButton_1;
    private static Vector<JRadioButton> chooseCountryButton_2;
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
        this.chooseCountryButton_1 = new Vector<JRadioButton>(CountryName.values().length);
        this.chooseCountryButton_2 = new Vector<JRadioButton>(CountryName.values().length);

        for (int i = 0; i < CountryName.values().length; i++) {
            String name = CountryName.values()[i].toString();
            JRadioButton btn = new JRadioButton(name);
            btn.setBounds(200 * i + 200, 300, 100, 50);
            this.chooseCountryButton_1.add(btn);
            this.chooseCountryButtonGroup_1.add(btn);
            this.panel.add(btn);
        }

        for (int i = 0; i < CountryName.values().length; i++) {
            String name = CountryName.values()[i].toString();
            JRadioButton btn = new JRadioButton(name);
            btn.setBounds(200 * i + 200, 400, 100, 50);
            this.chooseCountryButton_2.add(btn);
            this.chooseCountryButtonGroup_2.add(btn);
            this.panel.add(btn);
        }

        this.selectedCountryNames = new CountryName[2];
        this.selectedCountryNames[0] = CountryName.values()[0];
        this.selectedCountryNames[1] = CountryName.values()[1];
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == Setting.confirmButton) {
                mainWindow.convertToNextState(selectedCountryNames);
            }
        }
    }

    private class RadioButtonListener implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            if (chooseCountryButton_1.contains(event.getSource())) {

            }
        }

    }

}
