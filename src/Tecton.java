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
        System.out.println("-> addSpore()");
        System.out.println("Spore added to tecton");
    }
    public ArrayList<Tecton>getConnectedNeighbors(){
        System.out.println("->getConnectedNeighbors()");
        return connectedNeighbours;
    }
    public void setOccupiedByFungus(){
        System.out.println("-> setOccupiedByFungus()");
        FungalBody fb = new FungalBody();
    }

    public boolean decreaseSpore() {
        System.out.println("-> decreaseSpore()");
        System.out.println("Is there enough Spore on the tecton? Y/N");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }

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