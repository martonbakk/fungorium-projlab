import java.util.Scanner;

public class WeakTecton extends Tecton{

    public void runSpecialEffect(){
       
        System.out.println("Is there a Hyphal on this Tecton? Y/N");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equals("Y")) {

            System.out.println("Hyphal cannot grow here");

        }else {
            
            System.out.println("-> connectTecton()");
            
            this.connectTecton();
        
        }
    }
}
