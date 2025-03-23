import java.util.ArrayList;
import java.util.List;

public class Tecton {

    public Tecton(){
        System.out.println("new Tecton created");
    }

    public void addSpore() {
        System.out.println("Spore added to tecton");
    }

    public void decreaseSpore() {
        System.out.println("Spore disappeared from tecton");
    }

    public void disconnectTecton() {
        System.out.println("Hyphal disappeared");
    }

    public void connectTecton() {
        System.out.println("Hyphal grew");
    }

    public void runSpecialEffect(){}
}