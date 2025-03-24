import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printTestCases();
            
            String answer = scanner.nextLine();

            switch(answer){
                case "0":
                    return;
                case "1":
                    moveInsect();
                    break;
                case "2":
                    insectEatsSpore();
                    break;
                case "3":
                    insectCutHyphal();
                    break;
                case "4":
                    speedUpHyphalDevelopment();
                    break;
                case "5":
                    splitTecton();
                    break;
                case "6":
                    fungalBodyShootsSpore();
                    break;
                case "7":
                    break;
                case "8":
                    keepHyphalAlive();
                    break;
                case "9":
                    break;
                default:
                    System.out.println("Nincs ilyen teszt.");
                    break;
            }
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

    private static void keepHyphalAlive() {
        FungalBody fungus = new FungalBody();
        fungus.keepHyphalAlive();
    }

    // TODO
    /*private static void growHyphal(){
        FungalBody fungus = new FungalBody();
        fungus.growHyphal();
    }*/

    private static void printTestCases() {
        System.out.println("Choose Test");
        System.out.println("0. Exit");
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