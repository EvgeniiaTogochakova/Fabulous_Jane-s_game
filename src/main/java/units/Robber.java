package units;

public class Robber extends Spearman{
    public Robber() {
        super();
    }

    @Override
    public String about() {
        return "Разбойник " + name + " жизненных сил: " + currentHp + " сил для атаки: " + attack;
    }
}
