package units;

public class Robber extends Spearman{
    public Robber() {
        super();
    }

    @Override
    public void step() {
        super.step();
    }

    @Override
    public String getInfo() {
        return "Разбойник " + name + " жизненных сил: " + currentHp + " сил для атаки: " + attack;
    }
}
