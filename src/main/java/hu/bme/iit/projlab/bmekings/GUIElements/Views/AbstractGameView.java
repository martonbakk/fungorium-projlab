package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import javax.swing.JPanel;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;

public abstract class AbstractGameView extends JPanel {
    protected Controller controller;

    public AbstractGameView(Controller controller) {
        this.controller = controller;
    }

    public abstract void update();
}