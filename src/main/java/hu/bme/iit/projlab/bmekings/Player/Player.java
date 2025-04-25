package hu.bme.iit.projlab.bmekings.Player;

import hu.bme.iit.projlab.bmekings.Program.Params;
import hu.bme.iit.projlab.bmekings.Entities.Entity;



public abstract class Player{
    private String playerId;
    private int score;

    // Objectum lesz a csomagolo, kasztolni kell az egyes menupontokba és akkor nem rakjuk tele felesleges parameterekkel a cuccot
    public abstract void SelectAction(int actionType, Params params);


    public Player(String playerId) {
        this.playerId = playerId;
        this.score = 0;
    }
};