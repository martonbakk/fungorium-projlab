public class ToxicTecton extends Tecton {
    
    public ToxicTecton () {
        System.out.println("new ToxicTecton created");
    }


    public void runSpecialEffect(){
        System.out.println("-> disconnectTecton()");
        this.disconnectTecton();
    
    }
}
