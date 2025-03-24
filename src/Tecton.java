import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tecton {

    

    public ArrayList<Spore> spores = new ArrayList<>();
    public ArrayList<Tecton> neighbours = new ArrayList<>();
    public ArrayList<Tecton> connectedNeighbours = new ArrayList<>();

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
        Tecton t2;
        t2 = this.neighbours.get(0);
        System.out.println("-> connectTecton()");
        connectedNeighbours.add(t2);
        t2.connectedNeighbours.add(this);
        
        Hyphal h1=new Hyphal();
        System.out.println("t1 and t2 connected!");
    }

    public void getNeighbor(){
        System.out.println("-> getNeighbor()");
    }

    public void runSpecialEffect(){}
}