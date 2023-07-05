package units;

public class Spearman extends Unit {

    public Spearman() {
        super(100, 50, 25, 25, new int[]{1, 2, 3, 5});
    }

    @Override
    public void step() {

    }

    @Override
    public String getInfo() {
        return "Копейщик " + name + " жизненных сил: " + currentHp + " сил для удара копьем: " + attack;
    }

}
