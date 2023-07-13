package units;

import java.util.ArrayList;
import java.util.Arrays;
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
        target.getDamageByArcher();
    }

    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
        if (currentHp == 0) {
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
        System.out.println("Арбалетчик " + name + " метко выстрелил в " + enemyTarget.name);
        toAttack(enemyTarget);
        System.out.println();
        for (var unit : myOwnTeam) {
            if (unit instanceof Peasant && unit.currentHp > 0 && unit.state == "Stand") {
                unit.state = "Busy";
                ((Peasant) unit).arrows--;
                System.out.print("Арбалетчик " + this.name + " благодарит за стрелу крестьянина " + unit.name);
                System.out.println(". " + unit.name + " становится Busy");
                break;
            }
        }
        this.arrows--;
    }


    @Override
    public String getInfo() {
        return "Арбалетчик " + name + " hit points: " + currentHp + " стрел: " + arrows +
                " сил стрелять: " + attack + " C:" + Arrays.toString(getCoordinates());
    }
}

