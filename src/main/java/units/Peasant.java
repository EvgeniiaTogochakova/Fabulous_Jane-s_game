package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Peasant extends Unit {
    int arrows;

    public Peasant(int x, int y) {
        super(25, 15, 5, new int[]{5, 10, 15}, x, y);
        this.arrows = new Random().nextInt(5, 10);
    }

    @Deprecated
    public void toAttack(Unit target) {}


    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        if (this.currentHp == 0) return;
        if (state == "Busy") {
            state = "Stand";
        }
    }


    @Override
    public String getInfo() {
        return "Крестьянин " + name + " hp: " + currentHp + " стрел: " + arrows + " P:" + Arrays.toString(getCoordinates()) + " " + state;
    }

}



