package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Monk extends Unit {
    int elixir;
    int poison;

    public Monk(int x, int y) {
        super(25, 20, new Random().nextInt(25, 101), 5, new int[]{5, 10, 15}, x, y);
        this.elixir = new Random().nextInt(20, 30);
        this.poison = new Random().nextInt(20, 30);
    }

    private boolean healingAbility() {
        if (this.elixir >= 5) return true;
        return false;
    }

    public void healing(Unit patient) {
        if (healingAbility() && patient.currentHp < 15 && patient.currentHp > 0) {
            this.elixir -= 5;
            patient.currentHp += 5;
        }
        if (this.elixir < 0) this.elixir = 0;
    }


    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        if (this.currentHp == 0) return;
        int digit = new Random().nextInt(2);
        switch (digit) {
            case 0 -> {
                if (this.poison <5) return;
                ArrayList<Unit> liveOpponents = new ArrayList<>();
                for (var opponent : heroes) {
                    if (opponent.currentHp > 0) {
                        liveOpponents.add(opponent);
                    }
                }
                if (liveOpponents.size() == 0) return;
                Unit closestVictim = findClosestEnemy(liveOpponents);
//                    System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
                toAttack(closestVictim);
                this.poison -= 5;
                state = "Attack";
                if (this.poison < 0) this.poison = 0;
            }
            case 1 -> {
                if (!healingAbility()) return;
                ArrayList<Unit> liveTeammates = new ArrayList<>();
                for (var ally : myOwnTeam) {
                    if (ally.currentHp > 0) liveTeammates.add(ally);
                }
                if (liveTeammates.size() == 0) return;

                float minHp = 25;
                for (var unit : liveTeammates) {
                    if (unit.currentHp < minHp) {
                        minHp = unit.currentHp;
                    }
                }
                if (minHp >= 15 & this.poison>=5) {//если соратников пока лечить не нужно, монах, как и маг, умеет атаковать врагов
                    ArrayList<Unit> liveOpponents = new ArrayList<>();
                    for (var opponent : heroes) {
                        if (opponent.currentHp > 0) {
                            liveOpponents.add(opponent);
                        }
                    }
                    if (liveOpponents.size() == 0) return;
                    Unit closestVictim = findClosestEnemy(liveOpponents);
                    toAttack(closestVictim);
                    this.poison -= 5;
                    state = "Attack";
                    if (this.poison < 0) this.poison = 0;
                    return;
                }

                for (var unit : liveTeammates) {
                    if (unit.currentHp == minHp) {
                        healing(unit);
                        state = "Treated";
                        break;
                    }
                }
            }
        }

    }




    @Override
    public String getInfo() {
        return "Монах " + name + " hp: " + currentHp + " elixir: " + elixir +
                " poison: " + poison + " initiative: " + attack + " O:" + Arrays.toString(getCoordinates()) + " " + state;
    }

    @Override
    public String toString() {
        return "O";
    }
}
