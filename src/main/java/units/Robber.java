package units;

import java.util.ArrayList;
import java.util.Arrays;

public class Robber extends Spearman{
    public Robber(int x, int y) {
        super(x,y);
    }

    @Override
    public void step(ArrayList<Unit> heroes,ArrayList<Unit> myOwnTeam) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
    }

    @Override
    public String getInfo() {
        return "Разбойник " + name + " hit points: " + currentHp + " сил для атаки: " + attack + " R:" + Arrays.toString(getCoordinates());
    }
}
