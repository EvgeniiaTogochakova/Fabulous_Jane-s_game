package units;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;

public abstract class Unit implements GameInterface {
    protected static int id = 1;
    protected String name;
    protected float maxHp;
    protected float currentHp;
    public int attack;
    protected int defense;
    protected int[] damage;
    public Coordinates coordinates;

    public Unit(float maxHp, float currentHp, int attack, int defense, int[] damage, int x, int y) {
        this.name = getName();
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        ++id;
        coordinates = new Coordinates(x, y);

    }

    public Unit(float maxHp, float currentHp, int defense, int[] damage, int x, int y) {
        this.name = getName();
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.defense = defense;
        this.damage = damage;
        ++id;
        coordinates = new Coordinates(x, y);
    }


    protected static String getName() {
        String str = String.valueOf(ListNames.values()[new Random().nextInt(ListNames.values().length)]) + id;
        return str;
    }

//    public void healed(int Hp) {this.currentHp = Math.min(Hp + this.currentHp, this.maxHp);}

    public void getDamage() {
        int indexDamage = new Random().nextInt(this.damage.length);
        int realDamage = damage[indexDamage] - (int) (this.defense * 0.2);
        if (this.currentHp - realDamage > 0) {
            this.currentHp -= realDamage;
        } else {
            this.currentHp = 0;
            state = "Dead";
//            die();
        }
    }

    public void getDamageByArcher() {
        int averageDamage = 0;
        for (int i = 0; i < this.damage.length; i++) {
            averageDamage += damage[i];
        }
        averageDamage = averageDamage / this.damage.length;
        this.currentHp -= averageDamage;
        if (this.currentHp <= 0) {
            this.currentHp = 0;
            state = "Dead";
//            die();
        }
    }

    public void toAttack(Unit target) {
        target.getDamage();

    }

//    protected void die() {}

    @Override
    public String getInfo() {
        return name + " жизненных сил: " + currentHp;
    }

    public Unit findClosestEnemy(ArrayList<Unit> enemies) {
        double closestDistance = Double.MAX_VALUE;
        Unit closestEnemy = null;
        for (int i = 0; i < enemies.size(); i++) {
            if (this.coordinates.distanceСalculation(enemies.get(i).coordinates) < closestDistance) {
                closestDistance = this.coordinates.distanceСalculation(enemies.get(i).coordinates);
                closestEnemy = enemies.get(i);
            }
        }
        return closestEnemy;
    }


    public String state = "Stand";

    public int[] getCoordinates() {
        return new int[]{coordinates.x, coordinates.y};
    }

    public float getHp() {
        return currentHp;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void move(Coordinates targetEnemy, ArrayList<Unit> myOwnTeam){
        if (!coordinates.containsByPosition(coordinates.newPosition(targetEnemy), myOwnTeam)){
            coordinates = coordinates.newPosition(targetEnemy);
        }
    }

}
