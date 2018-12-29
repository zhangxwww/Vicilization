package team.vicilization.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import team.vicilization.country.*;

public class Setting extends State {

    // 确认
    private static JButton confirmButton;

    // 两组选择国家的按钮
    private static Vector<JRadioButton> chooseCountryButton_1;
    private static Vector<JRadioButton> chooseCountryButton_2;

    // 对应两个玩家的两个button group
    private ButtonGroup chooseCountryButtonGroup_1;
    private ButtonGroup chooseCountryButtonGroup_2;

    // 显示提示信息
    private JLabel chooseLeaderLabel;

    // 两位玩家选择的国家
    private CountryName[] selectedCountryNames;

    public Setting(MainWindow mainWindow) {
        super(mainWindow);
        setNextState(StateType.MainGame);

        this.addButtons();
        this.addLabels();
    }

    private void addLabels() {
        this.chooseLeaderLabel = new JLabel("Choose leader");
        this.chooseLeaderLabel.setBounds(860, 200, 200, 200);
        this.chooseLeaderLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        this.panel.add(chooseLeaderLabel);
    }

    private void addButtons() {
        this.addConfirmButton();
        this.addChooseCountryButtons();
    }

    private void addConfirmButton() {
        this.confirmButton = new JButton("Confirm");
        this.confirmButton.setBounds(860, 600, 200, 50);
        this.confirmButton.setFont(new Font("Consolas", Font.BOLD, 25));
        this.confirmButton.addActionListener(new ButtonListener());
        this.panel.add(confirmButton);
    }

    private void addChooseCountryButtons() {
        this.chooseCountryButtonGroup_1 = new ButtonGroup();
        this.chooseCountryButtonGroup_2 = new ButtonGroup();
        this.chooseCountryButton_1 = new Vector<JRadioButton>(CountryName.values().length);
        this.chooseCountryButton_2 = new Vector<JRadioButton>(CountryName.values().length);

        RadioButtonListener listener = new RadioButtonListener();

        for (int i = 0; i < CountryName.values().length; i++) {
            String name = CountryName.values()[i].toString();
            JRadioButton btn_1 = new JRadioButton(name);
            JRadioButton btn_2 = new JRadioButton(name);
            btn_1.setBounds(200 * i + 800, 400, 200, 50);
            btn_2.setBounds(200 * i + 800, 500, 200, 50);
            btn_1.setFont(new Font("Consolas", Font.BOLD, 25));
            btn_2.setFont(new Font("Consolas", Font.BOLD, 25));
            if (i == 0) {
                btn_1.setSelected(true);
            } else {
                btn_2.setSelected(true);
            }

            btn_1.addItemListener(listener);
            btn_2.addItemListener(listener);

            this.chooseCountryButton_1.add(btn_1);
            this.chooseCountryButton_2.add(btn_2);

            this.chooseCountryButtonGroup_1.add(btn_1);
            this.chooseCountryButtonGroup_2.add(btn_2);

            this.panel.add(btn_1);
            this.panel.add(btn_2);

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
            if (event.getStateChange() == ItemEvent.SELECTED) {
                if (chooseCountryButton_1.contains(event.getSource())) {
                    String country = ((JRadioButton) event.getSource()).getText().toUpperCase();
                    selectedCountryNames[0] = CountryName.valueOf(country);
                } else if (chooseCountryButton_2.contains(event.getSource())) {
                    String country = ((JRadioButton) event.getSource()).getText().toUpperCase();
                    selectedCountryNames[1] = CountryName.valueOf(country);
                }
            }
        }
    }

}
