public class Program {
    public static void main(String[] args) {
        mapSkeleton();
        insectSkeleton();
    }

    private static void mapSkeleton(){
        Map map = new Map();
        map.generateMap();
        map.splitTecton();
        map.updateTectons();
    }

    private static void insectSkeleton(){
        Insect insect = new Insect();
        insect.move();
        insect.eatSpore();
        insect.cutHyphal();
        insect.update();
    }

}