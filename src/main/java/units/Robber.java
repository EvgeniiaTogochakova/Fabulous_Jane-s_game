package units;

import java.util.ArrayList;

public class Robber extends Spearman{
    public Robber(int x, int y) {
        super(x,y);
    }

    @Override
    public void step(ArrayList<Unit> heroes) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
    }

    @Override
    public String getInfo() {
        return "Разбойник " + name + " жизненных сил: " + currentHp + " сил для атаки: " + attack;
    }
}
