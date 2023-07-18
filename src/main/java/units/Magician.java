package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Magician extends Unit {
    int mana;

    public Magician(int x, int y) {
        super(25, 20, new Random().nextInt(25, 101), 5, new int[]{5, 10, 15}, x, y);
        this.mana = new Random().nextInt(40, 50);
    }

    private boolean healingAbility() {
        if (this.mana >= 5) return true;
        return false;
    }

    public void healing(Unit patient) {
        if (healingAbility() && patient.currentHp < 15 && patient.currentHp > 0) {
            this.mana -= 5;
            patient.currentHp += 5;
        }
        if (this.mana < 0) this.mana = 0;
    }

    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        if (this.currentHp == 0) return;
        int digit = new Random().nextInt(2);
        switch (digit) {
            case 0 -> {
                if (this.mana <5) return;
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
                this.mana -= 5;
                state = "Attack";
                if (this.mana < 0) this.mana = 0;
            }
            case 1 -> {
                if (!healingAbility()) return;
                ArrayList<Unit> deadTeammates = new ArrayList<>();
                ArrayList<Unit> liveTeammates = new ArrayList<>();
                for (var ally : myOwnTeam) {
                    if (ally.currentHp == 0) deadTeammates.add(ally);
                    if (ally.currentHp > 0) liveTeammates.add(ally);
                }//вот тут сначала должен выявить умерших, если пало больше половины, взять первого по списку и лечить.
                //В ином случае просто лечить по-честному, у кого хуже ситуация.
                if (liveTeammates.size() == 0) return;
                if (deadTeammates.size() > liveTeammates.size() && this.mana >= 20) {
                    deadTeammates.get(0).currentHp = 15;
                    deadTeammates.get(0).state = "Stand";
                    this.mana = 0;
                    this.state = "Resurrected";
                    return;
                }

                float minHp = 25;
                for (var unit : liveTeammates) {
                    if (unit.currentHp < minHp) {
                        minHp = unit.currentHp;
                    }
                }
                if (minHp >= 15) {//Если маг хотел полечить, а соратникам лечение пока не показано, маг помогает им в атаке
                    ArrayList<Unit> liveOpponents = new ArrayList<>();
                    for (var opponent : heroes) {
                        if (opponent.currentHp > 0) {
                            liveOpponents.add(opponent);
                        }
                    }
                    if (liveOpponents.size() == 0) return;
                    Unit closestVictim = findClosestEnemy(liveOpponents);
                    toAttack(closestVictim);
                    this.mana -= 5;
                    state = "Attack";
                    if (this.mana < 0) this.mana = 0;
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
        return "Маг " + name + " hp: " + currentHp + " mana: " + mana +
                " initiative: " + attack + " М:" + Arrays.toString(getCoordinates()) + " " + state;
    }
}
