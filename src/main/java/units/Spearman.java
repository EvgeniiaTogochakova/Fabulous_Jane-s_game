package units;

import java.util.ArrayList;

public class Spearman extends Unit {

    public Spearman(int x, int y) {
        super(100, 50, 25, 25, new int[]{1, 2, 3, 5}, x,y);
    }

    @Override
    public void step(ArrayList<Unit> heroes) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
    }

    @Override
    public String getInfo() {
        return "Копейщик " + name + " жизненных сил: " + currentHp + " сил для удара копьем: " + attack;
    }

}
