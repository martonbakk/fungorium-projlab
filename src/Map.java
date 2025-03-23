import java.util.Scanner;

public class Map {
    public void generateMap() {

        System.out.println("Create Tectons:");
        Tecton t1 = new Tecton();
        ToxicTecton tt1 = new ToxicTecton();
        WeakTecton wt1 = new WeakTecton();
        NoFungusTecton nft1 = new NoFungusTecton();

        System.out.println("Create Spores");
        NormalSpore nsp1 = new NormalSpore(1);
        SlowSpore slowsp1 = new SlowSpore(1);
        SpeedSpore speedp1 = new SpeedSpore(1);
        StunSpore stunsp1 = new StunSpore(1);
        HyphalProtectorSpore hpsp1 = new HyphalProtectorSpore(1);
        HungerSpore hsp1 = new HungerSpore(1);

        Insect i1 = new Insect();
        Hyphal h1 = new Hyphal("id");
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
