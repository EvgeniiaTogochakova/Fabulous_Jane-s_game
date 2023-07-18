package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Sniper extends Crossbowman {

    public Sniper(int x, int y) {
        super(x, y);
    }

    @Override
    public String getInfo() {
        return "Снайпер " + name + " hp: " + currentHp + " стрел: " + arrows +
                " initiative: " + attack + " N:" + Arrays.toString(getCoordinates()) + " " + state;
    }

    @Override
    public String toString() {
        return "N";
    }
}