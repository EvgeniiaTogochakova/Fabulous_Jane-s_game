package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Spearman extends Unit {

    public Spearman(int x, int y) {
        super(100, 50, new Random().nextInt(25,101), 25, new int[]{1, 2, 3, 5, 9}, x,y);
    }

    @Override
    public void step(ArrayList<Unit> heroes,ArrayList<Unit> myOwnTeam) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
    }

    @Override
    public String getInfo() {
        return "Копейщик " + name + " hit points: " + currentHp + " сил для удара : " + attack + " S:" + Arrays.toString(getCoordinates());
    }

}
