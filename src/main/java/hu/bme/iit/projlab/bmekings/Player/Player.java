package hu.bme.iit.projlab.bmekings.Player;

public abstract class Player{
    private String playerId;
    private int score;

    public abstract void SelectAction();

    public Player(String playerId) {
        this.playerId = playerId;
        this.score = 0;
    }
};