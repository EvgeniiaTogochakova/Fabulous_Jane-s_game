package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Monk extends Unit {
    int elixir;
    int poison;

    public Monk(int x, int y) {
        super(100, 50, new Random().nextInt(25, 101), 25, new int[]{5, 10, 15}, x, y);
        this.elixir = new Random().nextInt(30, 50);
        this.poison = new Random().nextInt(30, 50);
    }

    private boolean healingAbility() {
        if (this.elixir >= 5) return true;
        return false;
    }

    public void healing(Unit patient) {
        if (!healingAbility()) {
            System.out.println(name + " уже растратил свой эликсир!");
        }
        if (patient.currentHp >= 50) {
            System.out.println(patient.name + " можно пока не лечить, жизненных сил у него на среднем уровне");
        }
        if (healingAbility() && patient.currentHp < 50) {
            int portion = (int) (this.elixir * 0.2);
            patient.currentHp += portion;
            this.elixir -= portion;
            System.out.println("Монах " + name + " полечил эликсиром " + patient.name);
        }
    }

    @Override
    protected boolean attackAbility() {
        if (this.attack >= 5 && this.poison >= 5) return true;
        return false;
    }

    @Override
    public void toAttack(Unit target) {
        if (this.attack < 5) {
            System.out.println("У " + name + " уже не осталось сил для распыления яда");
        }
        if (this.poison < 5) {
            System.out.println("У " + name + " уже не осталось яда для распыления");
        }
        if (attackAbility()) {
            target.getDamage();
            this.attack -= 5;
            this.poison -= 5;
            System.out.println("Монах " + name + " успешно распылил яд в лицо врага: " + target.name);
        }
    }


    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        if (this.currentHp > 0) {
            System.out.print("Монах " + this.name + " жив...");
            int digit = new Random().nextInt(2);
            switch (digit) {
                case 0 -> {
                    Unit closestVictim = findClosestEnemy(heroes);
                    System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
                    toAttack(closestVictim);
                }
                case 1 -> {
                    float minHp = 100;
                    for (var unit : myOwnTeam) {
                        if (unit.currentHp < minHp) {
                            minHp = unit.currentHp;
                        }
                    }
                    if (minHp >= 50) {
                        System.out.println("Монах " + this.name + " хотел было полечить, но соратники пока более-менее держатся, надо им помочь в атаке");
                        Unit closestVictim = findClosestEnemy(heroes);
                        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
                        toAttack(closestVictim);
                        return;
                    }
                    for (var unit : myOwnTeam) {
                        if (unit.currentHp == minHp) {
                            healing(unit);
                            break;
                        }
                    }
                }
            }
        } else {
            System.out.println("Монах " + this.name + " мертв...");
        }
    }


    @Override
    public String getInfo() {
        return "Монах " + name + " hit points: " + currentHp + " эликсира: " + elixir +
                " яда: " + poison + " сил травить: " + attack + " O:" + Arrays.toString(getCoordinates());
    }

    @Override
    public String toString() {
        return "O";
    }
}
