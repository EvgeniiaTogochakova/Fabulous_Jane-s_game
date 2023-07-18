package units;

import java.util.ArrayList;

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

    public Coordinates newPosition(Coordinates targetEnemyPosition){
        Coordinates currentPosition = new Coordinates(x,y);
        if (Math.abs(targetEnemyPosition.x - x) >= Math.abs(targetEnemyPosition.y - y)){
            if (targetEnemyPosition.x - x > 0) currentPosition.x ++;
            else currentPosition.x--;
        }

        if (Math.abs(targetEnemyPosition.x - x) < Math.abs(targetEnemyPosition.y - this.y)){
            if (targetEnemyPosition.y - y > 0) currentPosition.y ++;
            else currentPosition.y--;
        }

        return currentPosition;
    }

    public boolean containsByPosition(Coordinates potentialNewPosition, ArrayList<Unit> allies) {
        for (var unit : allies){
            if (unit.coordinates == potentialNewPosition) return true;
        }
        return false;
    }
}
