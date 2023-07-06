package units;

import java.util.ArrayList;
import java.util.Random;

public class Crossbowman extends Unit {
    int arrows;

    public Crossbowman(int x, int y) {
        super(100, 50, 25, 25, new int[]{5, 10, 15}, x, y );
        this.arrows = new Random().nextInt(10, 20);
    }

    protected boolean needArrows() {
        if (this.arrows == 0) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean attackAbility() {
        if (this.attack >= 5 && !needArrows()) return true;
        return false;
    }

    public void toAttack(Unit target) {
        if (needArrows()) {
            System.out.println("Арбалетчик " + name + " стрелять не может, закончились стрелы");
        }
        if (this.attack < 5) {
            System.out.println("Арбалетчик " + name + " уже исчерпал свои атакующие силы!");
        }

        if (attackAbility()) {
            target.getDamage();
            this.attack -= 5;
            this.arrows -= 1;
            System.out.println("Арбалетчик " + name + " метко выстрелил из арбалета в " + target.name);
        }
    }

    @Override
    public void step(ArrayList<Unit> heroes) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
    }

    @Override
    public String getInfo() {
        return "Арбалетчик " + name + " жизненных сил: " + currentHp + " количество стрел: " + arrows +
                " сил для стрельбы: " + attack;
    }
}

