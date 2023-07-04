package units;

import java.util.Random;

public class Crossbowman extends Unit {
    int arrows;

    public Crossbowman() {
        super(100, 50, 25, 25, new int[]{5, 10, 15});
        this.arrows = new Random().nextInt(10, 20);
    }

    @Override
    public String about() {
        return "Арбалетчик " + name + " жизненных сил: " + currentHp + " количество стрел: " + arrows +
                " сил для стрельбы: " + attack;
    }

    protected boolean needArrows() {
        if (this.arrows == 0) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean attackAbility() {
        if (this.attack >= 5 && !needArrows()) return true;
        return false;
    }

    public void toAttack(Unit target) {
        if (needArrows()) {
            System.out.println("Арбалетчик " + name + " стрелять не может, закончились стрелы");
        }
        if (this.attack < 5) {
            System.out.println("Арбалетчик " + name + " уже исчерпал свои атакующие силы!");
        }

        if (attackAbility()) {
            target.getDamage();
            this.attack -= 5;
            this.arrows -= 1;
            System.out.println("Арбалетчик " + name + " метко выстрелил из арбалета в " + target.name);
        }
    }
}

