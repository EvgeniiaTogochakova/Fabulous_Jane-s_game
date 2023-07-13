package units;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Peasant extends Unit {
    int arrows;

    public Peasant(int x, int y) {
        super(100, 50, 30, new int[]{5, 10, 15}, x, y);
        this.arrows = new Random().nextInt(40, 50);
    }


    private boolean hasArrows() {
        if (this.arrows > 0) {
            return true;
        }
        return false;
    }

    public void givesArrows(Crossbowman archer) {
        if (archer.needArrows()) {
            System.out.println(archer.name + " просит стрелы");
        }
        if (!hasArrows()) {
            System.out.println(this.name + " уже исчерпал свой запас стрел для помощи");
        }
        if (!archer.needArrows()) {
            System.out.println("У " + archer.name + " еще не закончились стрелы");
        }
        if (archer.needArrows() && hasArrows()) {
            archer.arrows += 1;
            this.arrows -= 1;
            System.out.println("Крестьянин " + name + " принес стрелу " + archer.name);
        }
    }

    @Deprecated
    public void toAttack(Unit target) {
    }


    @Override
    public void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam) {
        if (this.currentHp > 0) {
            System.out.print("Крестьянин " + this.name + " жив... ");
            if (state == "Busy") {
                state = "Stand";
                System.out.println("Крестьянин " + this.name + " изменил флаг с Busy на Stand");
            }
        }
        if (this.currentHp == 0) System.out.println("Крестьянин " + this.name + " мертв");
    }


    @Override
    public String getInfo() {
        return "Крестьянин " + name + " hit points: " + currentHp + " стрел: " + arrows + " P:" + Arrays.toString(getCoordinates());
    }

}



