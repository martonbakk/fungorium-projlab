public class Program {
    public static void main(String[] args) {
        Map map = new Map();
        map.generateMap();
        map.splitTecton();
        map.updateTectons();

        Insect insect = new Insect();
        insect.move();
        insect.eatSpore();
        insect.cutHyphal();
        insect.update();

    }
}