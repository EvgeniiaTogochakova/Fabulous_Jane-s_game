package units;

import java.util.ArrayList;
import java.util.Random;

public class Sniper extends Crossbowman {

    public Sniper(int x, int y) {
        super(x,y);
    }

    @Override
    public void toAttack(Unit target) {
        if (needArrows()) {
            System.out.println("Снайпер " + name + " стрелять не может, закончились стрелы");
        }
        if (this.attack < 5) {
            System.out.println("Снайпер " + name + " уже исчерпал свои атакующие силы!");
        }

        if (attackAbility()) {
            target.getDamage();
            this.attack -= 5;
            this.arrows -= 1;
            System.out.println("Снайпер " + name + ", спрятавшись на дереве, неожиданно выстрелил из лука в мишень: " + target.name);
        }
    }

    @Override
    public void step(ArrayList<Unit> heroes) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
    }

    @Override
    public String getInfo() {
        return "Снайпер " + name + " жизненных сил: " + currentHp + " количество стрел: " + arrows+
                " сил для стрельбы: " + attack;
    }
}