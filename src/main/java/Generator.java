import java.util.Random;

public class Generator {
    static Random r = new Random();


    public static int numberXWhiteRose(){
        return r.nextInt(1,6);
    }

    public static int numberXRedRose() {
        return r.nextInt(6, 11);
    }

    public static int numberYMagician() {
        return r.nextInt(1, 6);
    }

    public static int numberYMonk() {
        return r.nextInt(2, 7);
    }

    public static int numberYPeasant() {
        return r.nextInt(3, 8);
    }

    public static int numberYCrossbowman() {
        return r.nextInt(1, 6);
    }
    public static int numberYSniper() {
        return r.nextInt(6, 11);
    }

    public static int numberYSpearman() {
        return r.nextInt(5, 10);
    }

    public static int numberYRobber() {
        return r.nextInt(6, 11);
    }

}

