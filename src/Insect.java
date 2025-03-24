import java.util.Scanner;

public class Insect extends Entity {
    

    public Insect() {
        super();
        System.out.println("\tnew Insect");
    }

    public void move() {
        System.out.println("-> move");
        Scanner scanner = new Scanner(System.in);

        baseLocation.getConnectedNeighbors();
        System.out.println("Can the Insect move? Y/N");

        String answer = scanner.nextLine();
        if (answer.equals("N")) return;
        

        System.out.println("Are there any connected neighbors? Y/N");

        answer = scanner.nextLine();

        if (answer.equals("Y")) {
            System.out.println("-> step()");

        }
        else if (answer.equals("N")) {
            System.out.println("Nowhere to move");
        }
        else {
            System.out.println("Wrong input");
        }
    }

    public void eatSpore() {
        System.out.println("-> eatSpore()");

        Scanner scanner = new Scanner(System.in);

        System.out.println("-> decreaseSpores()");
        System.out.println("Are there any Spores on the Tecton? Y/N");
        String answer = scanner.nextLine();

        if (answer.equals("Y")) {
            System.out.println("What type of Spore is on the Tecton? \n\t1. Normal\t2.Stun\t3.Slow\t4.Speed\t5.Hunger\t6.HyphalProtector");
            answer = scanner.nextLine();
            System.out.println("-> activateEffect()");
            
        }
        else if (answer.equals("N")) {
            System.out.println("No Spore to eat!");
        }
        else {
            System.out.println("Wrong input");
        }
        
    }

    public void cutHyphal() {
        System.out.println("-> cutHyphal()");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is cut on cooldown? Y/N");

        String answer = scanner.nextLine();

        if (answer.equals("Y")) {
            System.out.println("Can't cut");
        }
        else if (answer.equals("N")) {
            System.out.println("-> disconnectTecton()");
            // függvényhívás
        }
        else {
            System.out.println("Wrong input");
        }
    }

    public void update() {
        
    }
}
