// Проанализировать и описать персонажей:
// Маг, монах, разбойник, копейщик, снайпер, арбалетчик, крестьянин.
// На базе описания персонажей описать простейшую иерархию классов.
// В основной программе создать по одному экземпляру каждого класса.

import units.*;

public class Main {
    public static void main(String[] args) {
        Magician magician = new Magician();
        System.out.println(magician.about());
        Monk monk = new Monk();
        System.out.println(monk.about());
        Robber robber = new Robber();
        System.out.println(robber.about());
        Spearman spearman = new Spearman();
        System.out.println(spearman.about());
        Sniper sniper = new Sniper();
        System.out.println(sniper.about());
        Crossbowman crossbowman = new Crossbowman();
        System.out.println(crossbowman.about());
        Peasant peasant = new Peasant();
        System.out.println(peasant.about());
        magician.toAttack(peasant);
        System.out.println(magician.about());
        System.out.println(peasant.about());
        spearman.toAttack(magician);
        System.out.println(spearman.about());
        System.out.println(magician.about());
        spearman.toAttack(magician);
        System.out.println(spearman.about());
        System.out.println(magician.about());
        spearman.toAttack(magician);
        System.out.println(spearman.about());
        System.out.println(magician.about());
        monk.healing(magician);
        magician.healing(magician);
        System.out.println(magician.about());















    }
}
