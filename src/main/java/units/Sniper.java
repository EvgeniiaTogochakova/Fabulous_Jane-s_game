package units;

import java.util.Random;

public class Sniper extends Crossbowman {

    public Sniper() {
        super();
    }

    @Override
    public String about() {
        return "Снайпер " + name + " жизненных сил: " + currentHp + " количество стрел: " + arrows+
                " сил для стрельбы: " + attack;
    }


    @Override
    public void toAttack(Unit target) {
        if (needArrows()) {
            System.out.println("Снайпер " + name + " стрелять не может, закончились стрелы");
        }
        if (this.attack < 5) {
            System.out.println("Снайпер " + name + " уже исчерпал свои атакующие силы!");
        }

        if (attackAbility()) {
            target.getDamage();
            this.attack -= 5;
            this.arrows -= 1;
            System.out.println("Снайпер " + name + ", спрятавшись на дереве, неожиданно выстрелил из лука в мишень: " + target.name);
        }
    }
}