package units;

import java.util.EnumMap;
import java.util.Random;

public abstract class Unit implements GameInterface{
    protected static int id = 1;
    protected String name;
    protected float maxHp, currentHp;
    protected int attack, defense;
    protected int[] damage;

    public Unit(float maxHp, float currentHp, int attack, int defense, int[] damage) {
        this.name = getName();
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        ++id;

    }

    public Unit(float maxHp, float currentHp, int defense, int[] damage) {
        this.name = getName();
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.defense = defense;
        this.damage = damage;
        ++id;
    }



    protected static String getName() {
        String str = String.valueOf(ListNames.values()[new Random().nextInt(ListNames.values().length)]) + id;
        return str;
    }

    public void healed(int Hp) {
        this.currentHp = Math.min(Hp + this.currentHp, this.maxHp);
    }

    public void getDamage() {
        int indexDamage = new Random().nextInt(this.damage.length);
        int realDamage = damage[indexDamage]- (int) (this.defense * 0.2);
        if (this.currentHp - realDamage > 0) {
            this.currentHp -= realDamage;
        } else {
            die();
        }
    }

    protected boolean attackAbility() {
        if (this.attack >= 5) return true;
        return false;
    }
    public void toAttack(Unit target) {
        if (attackAbility()) {
            target.getDamage();
            this.attack -= 5;
            System.out.println(name + " атаковал " + target.name);
        }else{
            System.out.println(name+ " уже исчерпал свои атакующие силы!");
        }
    }
    protected void die() {
    }

    @Override
    public String getInfo() {
        return name + " жизненных сил: " + currentHp;
    }

}
