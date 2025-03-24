import java.util.Scanner;

public class Hyphal extends Entity {

    public Hyphal() {
        super();
        System.out.println("new Hyphal");
    }

    public void growFungus() {
            if (baseLocation.decreaseSpore()){
                System.out.println("Hyphal is growing fungus");
                FungalBody fu = new FungalBody();
            } else {
                System.out.println("FungalBody cannot grow here");
            }

    }

    public void speedUpDevelopment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\"Are there enough spores on the target tecton? Y/N\""); 

        String answer = scanner.nextLine();

        if (answer.equals("Y")){

            System.out.println("->decreaseSpores()");
            System.out.println("Spores destroyed");
            System.out.println("Hyphal is speeding up development");
        }

    }

    public void aging() {
        System.out.println("->update");
        System.out.println("Hyphals lifetime decreased by one."); 
        
        
        System.out.println("Was this the Hyphals last life point? Y/N"); 
        
        
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equals("Y")) {

            System.out.println("Hyphal dies"); 
        } 

    }

    public void update() {
        System.out.println("Hyphal is updated");
    }

    public void increaseLifetime() {
        System.out.println("-> increaseLifetime()");
        System.out.println("Hyphal lifetime is refilled to max");
    }


}