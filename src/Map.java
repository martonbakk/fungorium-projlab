import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    ArrayList<Tecton> tectons = new ArrayList<>();

    public void generateMap() {

        System.out.println("Create Tectons:");
        Tecton t1 = new Tecton();
        ToxicTecton tt1 = new ToxicTecton();
        WeakTecton wt1 = new WeakTecton();
        NoFungusTecton nft1 = new NoFungusTecton();

        System.out.println("Create Spores");
        NormalSpore nsp1 = new NormalSpore();
        SlowSpore slowsp1 = new SlowSpore();
        SpeedSpore speedp1 = new SpeedSpore();
        StunSpore stunsp1 = new StunSpore();
        HyphalProtectorSpore hpsp1 = new HyphalProtectorSpore();
        HungerSpore hsp1 = new HungerSpore();

        Insect i1 = new Insect();
        Hyphal h1 = new Hyphal();
        FungalBody fgb1 = new FungalBody();
    }

    public void splitTecton() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-> splitTecton()");
        System.out.println("Is there an Insect on the Tecton? Y/N");

        String answer = scanner.nextLine();
        if (answer.equals("Y")) {
            System.out.println("Tecton cannot be split!");
        }
        else if (answer.equals("N")) {
            System.out.println("Destroy FungalBody");
            System.out.println("-> updateTectons()");
            updateTectons();
        }
        else {
            System.out.println("Wrong input");
        }
    }

    public void updateTectons() {
        System.out.println("-> t1.getConnectedNeighborsList()");
        
        System.out.println("disconnectTecton(t1)");
        System.out.println("Destroy t1");
        System.out.println("Create new Tecton");
        System.out.println("Create new Tecton");
    }

}
