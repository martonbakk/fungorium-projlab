package hu.bme.iit.projlab.bmekings.Player;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import hu.bme.iit.projlab.bmekings.Program.Params;



public abstract class Player implements Serializable{
    private static final long serialVersionUID = 1L;

    
    private String playerId;
    private int score;
    protected  String userName;
    protected String type;

    // Objectum lesz a csomagolo, kasztolni kell az egyes menupontokba és akkor nem rakjuk tele felesleges parameterekkel a cuccot
    public abstract void SelectAction(int actionType, Params params);


    public Player(String playerId) {
        this.playerId = playerId;
        this.score = 0;
    }

    public String getPlayerID() { return playerId; }

    public String getUserName(){return userName;}

    public int getScore() { return score; }

    public String toString() {
        return "Username: "+userName + " PlayerID: " + playerId+" Type: " + type;
    }

    public List<String> getAvailableActions() {
        return Arrays.asList("Mozgás", "Gyűjtés", "Vizsgálat"); // Teszt adatok
    }

    public String getType() {
        return this.type;
    }
};