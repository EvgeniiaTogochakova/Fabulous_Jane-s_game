package units;

import java.util.Random;

public class Peasant extends Unit {
    int arrows;

    public Peasant() {
        super(100, 50, 30, new int[]{5, 10, 15});
        this.arrows = new Random().nextInt(40, 50);
    }

    @Override
    public String about() {
        return "Крестьянин " + name + " жизненных сил: " + currentHp + " количество стрел: " + arrows;
    }

    private boolean hasArrows() {
        if (this.arrows > 0) {
            return true;
        }
        return false;
    }

    public void givesArrows(Crossbowman archer){
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
    public void toAttack(Unit target) {}

}



