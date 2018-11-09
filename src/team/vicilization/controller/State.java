package team.vicilization.controller;

import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.awt.*;

public abstract class State {

    protected MainWindow mainWindow;
    protected JPanel panel;

    static StateType nextState;

    public State(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.panel = new JPanel();
        this.panel.setBounds(0,0,1920,1080);
        this.panel.setLayout(null);
    }

    public JPanel getPanel() {
        return panel;
    }

    @Contract(pure = true)
    public StateType getNextState() {
        return nextState;
    }

    public void setNextState(StateType nextState) {
        State.nextState = nextState;
    }
}
