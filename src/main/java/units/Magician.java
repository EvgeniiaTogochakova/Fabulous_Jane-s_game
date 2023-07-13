package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Magician extends Unit {
    int mana;

    public Magician(int x, int y) {
        super(100, 50, new Random().nextInt(25, 101), 25, new int[]{5, 10, 15}, x, y);
        this.mana = new Random().nextInt(50, 100);
    }

    private boolean healingAbility() {
        if (this.mana >= 5) return true;
        return false;
    }

    public void healing(Unit patient) {
        if (!healingAbility()) {
            System.out.println(name + " уже растратил свою ману!");
        }
        if (patient.currentHp >= 50) {
            System.out.println(patient.name + " можно пока не лечить, жизненных сил у него на среднем уровне");
        }
        if (healingAbility() && patient.currentHp < 50) {
            int portionMana = (int) (this.mana * 0.2);
            patient.currentHp += portionMana;
            this.mana -= portionMana;
            System.out.println(name + " полечил маной " + patient.name);
        }
    }

    @Override
    protected boolean attackAbility() {
        if (this.attack >= 5 && this.mana >= 5) return true;
        return false;
    }

    @Override
    public void toAttack(Unit target) {
        if (this.attack < 5) {
            System.out.println("У " + name + " уже не осталось сил для черной магии");
        }
        if (this.mana < 5) {
            System.out.println("У " + name + " уже не осталось маны, чтобы заколдовать врага");
        }
        if (attackAbility()) {
            target.getDamage();
            this.attack -= 5;
            this.mana -= 5;
            System.out.println("Маг " + name + " навел чары ослабления на: " + target.name);
        }
    }

    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        if (this.currentHp > 0) {
            System.out.print("Маг " + this.name + " жив...");
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
                        System.out.println("Маг " + this.name + " хотел было полечить, но соратники пока более-менее держатся, надо им помочь в атаке");
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
            System.out.println(("Маг " + this.name + " мертв..."));
        }
    }


    @Override
    public String getInfo() {
        return "Маг " + name + " hit points: " + currentHp + " запас маны: " + mana +
                " черной магии: " + attack + " М:" + Arrays.toString(getCoordinates());
    }
}
