import java.util.ArrayList;
import java.util.List;

public class Tecton {

    

    ArrayList<Spore> spores = new ArrayList<>();
    ArrayList<Tecton> neighbours = new ArrayList<>();
    ArrayList<Tecton> connectedNeighbours = new ArrayList<>();

    public Tecton(){
        System.out.println("new Tecton created");
    }

    public void addSpore() {
        System.out.println("Spore added to tecton");
    }

    public void decreaseSpore() {
        System.out.println("Spore disappeared from tecton");
    }

    public void disconnectTecton() {
        System.out.println("Hyphal disappeared");
    }

    public void connectTecton() {
        System.out.println("Hyphal grew");
    }

    public ArrayList<Tecton> getNeighborLisTecton(){
        System.out.println("neighbour s list returned");
        return neighbours;
    }

    public void runSpecialEffect(){}
}