package units;

import static java.lang.Math.round;

public class Coordinates {
    int x,y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanceСalculation(Coordinates coordinates){
        int xDistance = coordinates.x - x;
        int yDistance = coordinates.y - y;
        return Math.round(Math.sqrt(Math.pow(xDistance,2) + Math.pow(yDistance,2)));
    }
}
