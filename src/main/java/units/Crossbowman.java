package units;

import java.util.ArrayList;
import java.util.Random;

public class Crossbowman extends Unit {
    int arrows;

    public Crossbowman(int x, int y) {
        super(100, 50, new Random().nextInt(25, 101), 25, new int[]{5, 10, 15}, x, y);
        this.arrows = new Random().nextInt(10, 20);
    }

    protected boolean needArrows() {
        if (this.arrows == 0) {
            return true;
        }
        return false;
    }


    @Override
    public void toAttack(Unit target) {
        this.attack -= 5;
        System.out.println("Атакующих сил у "+ name + " стало меньше, теперь "+ attack);
        target.getDamageByArcher();
        System.out.println("У жертвы " + target.name + " в результате атаки уровень жизненных сил понизился до " + target.currentHp );
    }

    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
        if (currentHp <= 0.1) {
            System.out.println("Арбалетчик " + name + " уже не живой!");
            return;
        }
        if (this.attack < 5) {
            System.out.println("Арбалетчик " + name + " уже исчерпал свои атакующие силы!");
            return;
        }
        if (needArrows()) {
            System.out.println("Арбалетчик " + name + " стрелять не может, закончились стрелы");
            return;
        }
        Unit enemyTarget = findClosestEnemy(heroes);
        System.out.println("Арбалетчик " + name + " метко выстрелил из арбалета в " + enemyTarget.name);
        toAttack(enemyTarget);
        System.out.println();
        if (myOwnTeam.contains(Peasant.class)) {
            return;
        }
        this.arrows--;
    }


    @Override
    public String getInfo() {
        return "Арбалетчик " + name + " жизненных сил: " + currentHp + " количество стрел: " + arrows +
                " сил для стрельбы: " + attack;
    }
}

