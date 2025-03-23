import java.util.Scanner;

public class WeakTecton extends Tecton{

    public WeakTecton () {
        System.out.println("new WeakTecton created");
    }


    public void runSpecialEffect(){
       
        System.out.println("Is there a Hyphal on this Tecton? Y/N");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equals("Y")) {

            System.out.println("Hyphal cannot grow here");

        }else if (answer.equals("N")){
            
            System.out.println("-> connectTecton()");
            
            this.connectTecton();
        
        } else {
            System.out.println("Wrong Input!");
        }
    }
}
