package units;

public class Spearman extends Unit {

    public Spearman() {
        super(100, 50, 25, 25, new int[]{1, 2, 3, 5});
    }

    @Override
    public String about() {
        return "Копейщик " + name + " жизненных сил: " + currentHp + " сил для удара копьем: " + attack;
    }
}
