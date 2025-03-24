import java.util.ArrayList;

public class GameLogic {

    ArrayList<Entity> entitis = new ArrayList<>();

    ArrayList<Listener> listeners = new ArrayList<>();


    public GameLogic() {
        System.out.println("\tnew GameLogic");
    }

    public void startGame() {
        System.out.println("-> generateMap()");
    }

    public void timeTick() {
        System.out.println("-> timeTick()");
    }

    public void addListener() {
        System.out.println("-> Listener added");
    }

}