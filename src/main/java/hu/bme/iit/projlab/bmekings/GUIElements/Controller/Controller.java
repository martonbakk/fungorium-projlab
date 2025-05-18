package hu.bme.iit.projlab.bmekings.GUIElements.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hu.bme.iit.projlab.bmekings.GUIElements.Views.AbstractGameView;
import hu.bme.iit.projlab.bmekings.GUIElements.Views.GameView;
import hu.bme.iit.projlab.bmekings.GUIElements.Views.LoadView;
import hu.bme.iit.projlab.bmekings.GUIElements.Views.PlayerAddView;
import hu.bme.iit.projlab.bmekings.GUIElements.Views.SaveView;
import hu.bme.iit.projlab.bmekings.GUIElements.Views.StartView;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class Controller {
    private GameLogic gameLogic;
    private JFrame frame;
    private Map<String, AbstractGameView> views;
    private List<List<String>> textures;
    private List<String> insectTypes= new ArrayList<>();
    private List<String> fungalTypes=new ArrayList<>();

    public Controller(GameLogic gameLogic, JFrame frame, List<List<String>> textures, List<String> insectSubTypes, List<String> fungalSubTypes) {
        this.insectTypes = insectSubTypes;
        this.fungalTypes = fungalSubTypes;
        this.gameLogic = gameLogic;
        this.frame = frame;
        this.views = new HashMap<>();
        this.textures = textures;
        initializeViews();
    }

    private void initializeViews() {
        views.put("StartView", new StartView(this)); //StartView
        views.put("PlayerAddView", new PlayerAddView(this, this.insectTypes, this.fungalTypes)); //PlayerAddView
        views.put("GameView", new GameView(this, this.textures, this.insectTypes, this.fungalTypes));
        views.put("LoadView", new LoadView(this));
        views.put("SaveView", new SaveView(this));
    }

    public void switchView(String viewName) {
        frame.getContentPane().removeAll();
        AbstractGameView view = views.get(viewName);
        if (view == null) {
            System.err.println("Hiba: A " + viewName + " nézet nem található!");
            return;
        }
        if (viewName.equals("GameView")) {
            gameLogic.startGame();
        }
        frame.add(view);
        view.update();
        frame.revalidate();
        frame.repaint();
    }

    public void addPlayer(String name, String type, String subType) {
        if (type.equals("Mycologist")) {
            GameLogic.addMycologist(new Mycologist(name, subType));
        } else if (type.equals("Entomologist")) {
            GameLogic.addEntomologist(new Entomologist(name, subType));
        }
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.addAll(GameLogic.getMycologists());
        players.addAll(GameLogic.getEntomologists());
        return players;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public JFrame getFrame(){
        return frame;
    }

    public void saveGameOnExit(String name){
        try {
            gameLogic.saveGame(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadGameFromController(String name) {

        try {
            gameLogic = GameLogic.loadGame(name);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


}




