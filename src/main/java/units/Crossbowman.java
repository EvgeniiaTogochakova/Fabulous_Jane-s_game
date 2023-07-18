package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Crossbowman extends Unit {
    int arrows;

    public Crossbowman(int x, int y) {
        super(25, 15, new Random().nextInt(25, 101), 5, new int[]{5, 10, 15}, x, y);
        this.arrows = new Random().nextInt(10, 15);
    }


    @Override
    public void toAttack(Unit target) {
        target.getDamageByArcher();
    }

    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        if (this.currentHp == 0) return;
        ArrayList<Unit> liveOpponents = new ArrayList<>();
        for (var liveOpponent : heroes) {
            if (liveOpponent.currentHp > 0) liveOpponents.add(liveOpponent);
        }
//        System.out.println(closestVictim.name + " " + this.coordinates.distanceСalculation(closestVictim.coordinates));
        if (liveOpponents.size() == 0) return;
        Unit enemyTarget = findClosestEnemy(liveOpponents);
        if (this.arrows > 0) {
            toAttack(enemyTarget);
            arrows--;
            for (var unit : myOwnTeam) {
                if (unit instanceof Peasant && unit.currentHp > 0 && unit.state == "Stand" && ((Peasant) unit).arrows > 0) {
                    unit.state = "Busy";
                    ((Peasant) unit).arrows--;
                    this.arrows++;
                    break;
                }
            }
            state = "Shot";
            return;
        }
        if (this.arrows == 0) {
            if (coordinates.distanceСalculation(enemyTarget.coordinates) < 2) {
                toAttack(enemyTarget);
                state = "Attack";
            } else {
                move(enemyTarget.coordinates, myOwnTeam);
                state = "Moving";
            }
        }
    }


    @Override
    public String getInfo() {
        return "Арбалетчик " + name + " hp: " + currentHp + " стрел: " + arrows +
                " initiative: " + attack + " C:" + Arrays.toString(getCoordinates()) + " " + state;
    }
}

