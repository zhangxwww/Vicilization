package team.vicilization.controller;

import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.awt.*;

public abstract class State {

    protected MainWindow mainWindow;
    protected javax.swing.JPanel panel;

    static private StateType nextState;

    public State(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.panel = new JPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    @Contract(pure = true)
    public static StateType getNextState() {
        return nextState;
    }

    public static void setNextState(StateType nextState) {
        State.nextState = nextState;
    }
}
