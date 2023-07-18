import java.util.Random;

public class Generator {
    static Random r = new Random();


    public static int numberXWhiteRose(){
        return r.nextInt(1,4);
    }

    public static int numberXRedRose() {
        return r.nextInt(8, 11);
    }


}

