package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Spearman extends Unit {

    public Spearman(int x, int y) {
        super(25, 15, new Random().nextInt(25,101), 5, new int[]{1, 2, 3, 5, 9}, x,y);
    }

    @Override
    public void step(ArrayList<Unit> heroes,ArrayList<Unit> myOwnTeam) {
        if (this.currentHp == 0) return;
        ArrayList<Unit> liveOpponents = new ArrayList<>();
        for (var liveOpponent:heroes) {
            if (liveOpponent.currentHp > 0 ) liveOpponents.add(liveOpponent);
        }
        if (liveOpponents.size() == 0) return;
        Unit enemyToKill = findClosestEnemy(liveOpponents);
        if (coordinates.distanceСalculation(enemyToKill.coordinates) < 2){
            toAttack(enemyToKill);
            state = "Attack";
        }else{
            move(enemyToKill.coordinates, myOwnTeam);
            state = "Moving";
        }
    }

    @Override
    public String getInfo() {
        return "Копейщик " + name + " hp: " + currentHp + " initiative : " + attack + " S:" + Arrays.toString(getCoordinates()) + " " + state;
    }

}
