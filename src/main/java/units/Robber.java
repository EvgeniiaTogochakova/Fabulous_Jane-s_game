package units;

import java.util.ArrayList;
import java.util.Arrays;

public class Robber extends Spearman{
    public Robber(int x, int y) {
        super(x,y);
    }


    @Override
    public String getInfo() {
        return "Разбойник " + name + " hp: " + currentHp + " initiative: " + attack + " R:" + Arrays.toString(getCoordinates()) + " " + state;
    }
}
