import java.util.Scanner;

public class Hyphal extends Entity {

    public Hyphal() {
        super();
        System.out.println("new Hyphal");
    }

    public void growFungus() {
        System.out.println("Hyphal is growing fungus");

    }

    public void speedUpDevelopment() {
        System.out.println("Hyphal is speeding up development");
    }

    public void aging() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("->update");

        System.out.println("\"Are there any connected fungalbodies? Y/N\"");    

        String answer = scanner.nextLine();
        boolean check=true;

        if (answer.equals("Y")){
            System.out.println("Hyphal does not age.");    

        }
        while (check) {}
            
        
        if (answer.equals("N")){
            System.out.println("->aging");    

            System.out.println("\"Does the hyphal have any more lives? Y/N\"");    
            answer = scanner.nextLine();
        
    

        if (answer.equals("Y")){
            System.out.println("Repeats the first part.");    

        }

        if (answer.equals("N")){
            System.out.println("Hphal dies");    
        }

        }
    
    }

    public void update() {
        System.out.println("Hyphal is updated");
    }
}