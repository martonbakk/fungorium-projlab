public class ToxicTecton extends Tecton {
    private double hyphalDestroyTime;

    public void runSpecialEffect(){
        System.out.println("-> disconnectTecton()");
        this.disconnectTecton(null);
    }
}
