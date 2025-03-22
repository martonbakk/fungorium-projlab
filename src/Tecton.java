import java.util.ArrayList;
import java.util.List;

public class Tecton {
    private String id;
    private double splitChance;
    private boolean occupiedByInsect;
    private boolean occupiedByFungus;
    private List<Tecton> connectedNeighbours;
    private List<Spore> addSpores;
    private List<Spore> decreaseSpores;

    public Tecton(){
        System.out.println("Tecton created");
    }

    public Tecton(String id) {
        System.out.println("Tecton created");
    }

    public void addSpore(Spore spore) {
        System.out.println("Spore added to tecton");
    }

    public void decreaseSpore(Spore spore) {
        System.out.println("Spore disappeared from tecton");
    }

    public void disconnectTecton(Tecton tc) {
        System.out.println("Hyphal disappeared");
    }

    public void connectTecton(Tecton tc) {
        System.out.println("Hyphal grew");
    }

    public String getId() { return null; }

    public List<Tecton> getConnectedNeighbours() { return null;}
}