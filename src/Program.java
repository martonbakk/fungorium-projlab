import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            
        // fuggveny
            
            
            String answer = scanner.nextLine();

            switch(answer){   
                case "1":
                    splitSkeleton();
                case "2":
                    insectSkeleton();
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "10":
                case "11":
                case "12":
                case "13":
                case "14":
                case "15":
                default:
                System.out.println("Nincs ilyen teszt.");
            }

            break;
        }


    }



    private static void splitTecton(){
        Map map = new Map();
        map.splitTecton();
    }


    private static void insectEatsSpore(){
        Insect insect = new Insect();
        insect.eatSpore();
    }

    
    private static void moveInsect() {
        Insect insect = new Insect();
        insect.move();
    }
    
    private static void insectCutHyphal(){
        Insect insect = new Insect();
        insect.cutHyphal();
    }

    private static void speedUpHyphalDevelopment(){
        Hyphal hyphal = new Hyphal();
        hyphal.speedUpDevelopment();
    }

    private static void fungalBodyShootsSpore(){
        FungalBody fungus = new FungalBody();
        fungus.shootSpore();
    }

    private static void growHyphal(){
        FungalBody fungus = new FungalBody();
        fungus.growHyphal();
    }

    private static void printTestCases() {
        System.out.println("Choose Test");
        System.out.println("1. Move Insect");
        System.out.println("2. Insect Eats Spore");
        System.out.println("3. Insect Cuts Hyphal");
        System.out.println("4. Speed up Hyphal Development");
        System.out.println("5. Split Tecton");
        System.out.println("6. FungalBody Shoots Spore");
        System.out.println("7. Grow Hyphal");
        System.out.println("7. Grow FungalBody");
        System.out.println("8. Keep Connected Hyphals Alive");
        System.out.println("9. Hyphal dies due to aging");
    }

}