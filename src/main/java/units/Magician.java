package units;
import java.util.Random;

public class Magician extends Unit {
    int mana;

    public Magician() {
        super(100, 50, 25, 25, new int[]{5, 10, 15});
        this.mana = new Random().nextInt(50, 100);
    }

    private boolean healingAbility() {
        if (this.mana >= 5) return true;
        return false;
    }

    public void healing(Unit patient) {
        if (patient.currentHp < 50) {
            System.out.println(patient.name + " был бы благодарен за лечение магией");
        }
        if (!healingAbility()) {
            System.out.println(name + " уже растратил свою ману!");
        }
        if (healingAbility() && patient.currentHp < 50) {
            int portionMana = (int) (this.mana * 0.2);
            patient.currentHp += portionMana;
            this.mana -= portionMana;
            System.out.println(name + " полечил маной " + patient.name);
        }
    }

    @Override
    public String about() {
        return "Маг " + name + " жизненных сил: " + currentHp + " запас маны: " + mana +
                " сил для ослабления врага: " + attack;
    }

    @Override
    protected boolean attackAbility() {
        if (this.attack >= 5 && this.mana >=5) return true;
        return false;
    }

    @Override
    public void toAttack(Unit target) {
        if (this.attack < 5) {
            System.out.println("У " + name + " уже не осталось сил для черной магии");
        }
        if (this.mana < 5) {
            System.out.println("У " + name + " уже не осталось маны, чтобы заколдовать врага");
        }
        if (attackAbility()) {
            target.getDamage();
            this.attack -= 5;
            this.mana -= 5;
            System.out.println("Маг " + name + " навел чары ослабления на: " + target.name);
        }
    }
}
