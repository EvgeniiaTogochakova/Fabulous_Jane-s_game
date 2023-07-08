package units;

import java.util.ArrayList;
import java.util.Random;

public class Sniper extends Crossbowman {

    public Sniper(int x, int y) {
        super(x,y);
    }

    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
        if (currentHp <= 0.1) {
            System.out.println("Снайпер " + name + " уже не живой!");
            return;
        }
        if (this.attack < 5) {
            System.out.println("Снайпер " + name + " уже исчерпал свои атакующие силы!");
            return;
        }
        if (needArrows()) {
            System.out.println("Снайпер " + name + " стрелять не может, закончились стрелы");
            return;
        }
        Unit enemyTarget = findClosestEnemy(heroes);
        System.out.println("Снайпер " + name + " , спрятавшись на дереве, неожиданно выстрелил из лука в мишень: " + enemyTarget.name);
        toAttack(enemyTarget);
        System.out.println();
        if (myOwnTeam.contains(Peasant.class)) {
            return;
        }
        this.arrows--;
    }

    @Override
    public String getInfo() {
        return "Снайпер " + name + " жизненных сил: " + currentHp + " количество стрел: " + arrows+
                " сил для стрельбы: " + attack;
    }
}