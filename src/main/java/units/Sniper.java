package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Sniper extends Crossbowman {

    public Sniper(int x, int y) {
        super(x, y);
    }

    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        Unit closestVictim = findClosestEnemy(heroes);
        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
        if (currentHp == 0) {
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
        System.out.println("Снайпер " + name + " из западни неожиданно выстрелил из лука в мишень: " + enemyTarget.name);
        toAttack(enemyTarget);
        System.out.println();
        for (var unit : myOwnTeam) {
            if (unit instanceof Peasant && unit.currentHp > 0 && unit.state == "Stand") {
                unit.state = "Busy";
                ((Peasant) unit).arrows--;
                System.out.print("Снайпер " + this.name + " благодарит за стрелу крестьянина " + unit.name);
                System.out.println(". " + unit.name + " становится Busy");
                break;
            }
        }
        this.arrows--;
    }

    @Override
    public String getInfo() {
        return "Снайпер " + name + " hit points: " + currentHp + " стрел: " + arrows +
                " сил стрелять: " + attack + " N:" + Arrays.toString(getCoordinates());
    }

    @Override
    public String toString() {
        return "N";
    }
}