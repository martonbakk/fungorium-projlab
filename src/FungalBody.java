import java.util.Scanner;

public class FungalBody extends Entity {

    public FungalBody() {
        super();
        System.out.println("\t new FungalBody");
    }

    public void shootSpore() {
        System.out.println("-> shootSpore()");
        System.out.println("-> tecton.getNeighbour()");
        System.out.println("-> Spore()");
        System.out.println("-> nt1.addSpore()");
        System.out.println("-> Spore()");
        System.out.println("-> nt2.addSpore()");
        
    }

    public void levelUp() {
    int allapot = 1;  
    Scanner scanner = new Scanner(System.in);

    
    while (true) {
        System.out.println("Do you want to Level up? Y/N/S?");
        
        String answer = scanner.nextLine().toUpperCase();
        
        if (answer.equals("Y")) {
            
           
            
            
            if (allapot < 3) {
                System.out.println("-> levelUp()");
                allapot += 1;
                System.out.println("Your fungus is now lvl " + allapot);
            } else {
                System.out.println("Cannot level up! Your fungus is already at maximum level (3)");
            }
        }
        else if (answer.equals("N")) {
            System.out.println("Your fungus remains at lvl " + allapot);
        }
        else if (answer.equals("S")) {
            System.out.println("Stopping. Final level: " + allapot);
            break;
        }
    }
    scanner.close();


    }


    public void keepHyphalAlive() {
        System.out.println("-> keepHyphalAlive()");

        System.out.println("-> getConnectedHyphals()");

        System.out.println("Increase lifetime of all connected Hyphals.");

    }

    public void update() {
        System.out.println("FungalBody updated");
    }
}
