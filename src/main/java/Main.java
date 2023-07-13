//Реализовать визуализацию своего проекта по примеру семинара с использованием приложенных классов.
//Доработать степ крестьян и магов!

//В моей интерпретации игры маги и монахи сами решают, атаковать им или лечить в их step. Но если они решили все же лечить,
//а лечить пока особо никого не надо в начале игры, тогда они могут и атаковать, чтобы зря не пропадал их ход. Если лечить надо,
//действуют по-честному: лечат того соратника, который сильнее пострадал.
//Оказалось, на поле 10*10 трудно расставить персонажей, чтобы всех было хорошо видно, если заполнять команды рандомно.
// Некоторые игроки стоят на том же месте, что и их соратники. Правда, таких немного.
//У меня монахи берут вторую букву названия класса - O, как и снайперы - N.



import units.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class Main {
    public static ArrayList<Unit> teamWhiteRose = new ArrayList<>();
    public static ArrayList<Unit> teamRedRose = new ArrayList<>();
    public static ArrayList<Unit> commonList = new ArrayList<>();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            int digit = new Random().nextInt(7);
            switch (digit) {
                case 0 -> {
                    teamWhiteRose.add(new Monk(Generator.numberXWhiteRose(), Generator.numberYMonk()));
                    teamRedRose.add(new Monk(Generator.numberXRedRose(), Generator.numberYMonk()));
                }
                case 1 -> {
                    teamWhiteRose.add(new Magician(Generator.numberXWhiteRose(), Generator.numberYMagician()));
                    teamRedRose.add(new Magician(Generator.numberXRedRose(), Generator.numberYMagician()));
                }
                case 2 -> {
                    teamWhiteRose.add(new Peasant(Generator.numberXWhiteRose(), Generator.numberYPeasant()));
                    teamRedRose.add(new Peasant(Generator.numberXRedRose(), Generator.numberYPeasant()));
                }
                case 3 -> {
                    teamWhiteRose.add(new Crossbowman(Generator.numberXWhiteRose(), Generator.numberYCrossbowman()));
                    teamRedRose.add(new Crossbowman(Generator.numberXRedRose(), Generator.numberYCrossbowman()));
                }
                case 4 -> {
                    teamWhiteRose.add(new Sniper(Generator.numberXWhiteRose(), Generator.numberYSniper()));
                    teamRedRose.add(new Sniper(Generator.numberXRedRose(), Generator.numberYSniper()));
                }
                case 5 -> {
                    teamWhiteRose.add(new Spearman(Generator.numberXWhiteRose(), Generator.numberYSpearman()));
                    teamRedRose.add(new Spearman(Generator.numberXRedRose(), Generator.numberYSpearman()));
                }
                case 6 -> {
                    teamWhiteRose.add(new Robber(Generator.numberXWhiteRose(), Generator.numberYRobber()));
                    teamRedRose.add(new Robber(Generator.numberXRedRose(), Generator.numberYRobber()));
                }
            }
        }

        System.out.println("_".repeat(92));
        System.out.println("Приветствуйте составы команд! Нечетные игроки - команда WhiteRose, четные - команда RedRose: ");
        System.out.println("_".repeat(92));

        for (int i = 0; i < teamWhiteRose.size(); i++) {
            System.out.println(teamWhiteRose.get(i).getInfo());
            System.out.println(teamRedRose.get(i).getInfo());
        }

        commonList.addAll(teamWhiteRose);
        commonList.addAll(teamRedRose);

        commonList.sort(reverseOrder(comparing(Unit -> Unit.attack)));
        Scanner in = new Scanner(System.in);
        View.view();
        in.nextLine();
        System.out.println("_".repeat(63));
        System.out.println("Вывожу step персонажей в порядке убывания объема атакующих сил");
        System.out.println("_".repeat(63));
        commonList.forEach(n -> {
            String[] strings = n.getInfo().split(" ");
            System.out.print(strings[0] + " " + strings[1] + " делает ход: ");
            if (teamWhiteRose.contains(n)) n.step(teamRedRose, teamWhiteRose);
            if (teamRedRose.contains(n)) n.step(teamWhiteRose, teamRedRose);
        });


    }

}
