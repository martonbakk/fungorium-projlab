public class WeakTecton extends Tecton{
    private boolean hyphalExistsHere;

    public void runSpecialEffect(){
        if (this.hyphalExistsHere) {
            System.out.println("Hyphal cannot grow here");
        }else {
            System.out.println("-> connectTecton()");
            this.connectTecton(null);
        }
    }
}
