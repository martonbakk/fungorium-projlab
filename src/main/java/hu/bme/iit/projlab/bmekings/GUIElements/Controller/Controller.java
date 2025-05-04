package hu.bme.iit.projlab.bmekings.GUIElements.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import hu.bme.iit.projlab.bmekings.GUIElements.Models.PlayerModel;
import hu.bme.iit.projlab.bmekings.GUIElements.Views.AbstractGameView;
import hu.bme.iit.projlab.bmekings.GUIElements.Views.GameView;
import hu.bme.iit.projlab.bmekings.GUIElements.Views.StartView;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class Controller {
    private final PlayerModel model;
    private JFrame frame;
    private Map<String, AbstractGameView> views;

    public Controller(PlayerModel model, JFrame frame) {
        this.model = model;
        this.frame = frame;
        this.views = new HashMap<>();
        initializeViews();
    }

    private void initializeViews() {
        views.put("StartView", new StartView(this));
        views.put("GameView", new GameView(this));
    }

    public void switchView(String viewName) {
        frame.getContentPane().removeAll();
        AbstractGameView view = views.get(viewName);
        frame.add(view);
        view.update();
        frame.revalidate();
        frame.repaint();
    }

    public void addPlayer(String name, String type) {
        model.addPlayer(name, type);
    }

    public ArrayList<Player> getPlayers() {
        return model.getPlayers();
    }
}