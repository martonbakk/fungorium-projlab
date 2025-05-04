package hu.bme.iit.projlab.bmekings.GUIElements.Models;

import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class PlayerModel {
    private ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(String name, String type) {
        if (type.equals("Entomologist")) {
            players.add(new Entomologist(name));
        }else {
           players.add(new Mycologist(name));
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}