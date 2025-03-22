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

    public Tecton(){}

    public Tecton(String id) {}

    public void addSpore(Spore spore) {}

    public void decreaseSpore(Spore spore) {}

    public void disconnectTecton(Tecton tc) {}

    public void connectTecton(Tecton tc) {}

    public String getId() { return null; }

    public List<Tecton> getConnectedNeighbours() { return null;}
}