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

    public Tecton(String id) {
        this.id = id;
        this.splitChance = 0.0;
        this.occupiedByInsect = false;
        this.occupiedByFungus = false;
        this.connectedNeighbours = new ArrayList<>();
        this.addSpores = new ArrayList<>();
        this.decreaseSpores = new ArrayList<>();
    }

    public void addSpore(Spore spore) {
         
    }

    public void decreaseSpore(Spore spore) {
        
    }

    public void disconnectTecton(Tecton tc) {
        
    }

    public void connectTecton(Tecton tc) {
         
    }

    // Getters and setters
    public String getId() {
        return new String(" ");
    }

    public List<Tecton> getConnectedNeighbours() {
        return new List<Tecton>();
    }
}