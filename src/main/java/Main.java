//Делаем ход пехоты!
//1. Проверяем здоровье
//2. Ищем ближайшего врага
//3. Двигаемся в сторну врага
//4. Не наступаем на живых персонажей
//5. Если расстояние до врага одна клетка бъём его!

//У меня монахи берут вторую букву названия класса - O, как и снайперы - N.
//Маги и монахи в моей игре сами решают, атаковать им или лечить. Если выбрали лечить, а здоровье соратников пока не требует лечения,
//тогда они атакуют. Маги еще умеют воскрешать (если павших союзников больше, чем живых). Их мана обнуляется при этом.
//Лучники, когда стрелы заканчиваются, начинают двигаться, как пехотинцы.
//View вывожу после step, чтобы было нагляднее.
//Когда у магов маны меньше 5, они становятся бесполезными и для лечения, и для атаки.
//Когда у монахов эликсира меньше 5, они бесполезны для лечения
// . Если и яда меньше 5, то бесполезны и для атаки.

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

    public static boolean isTeamDead (ArrayList<Unit> team) {
        int deadUnits = 0;
        for (var unit: team) {
            if (unit.getHp() == 0) deadUnits++;
        }
        if (deadUnits == team.size()) return true;
        return false;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            int digit = new Random().nextInt(7);
            switch (digit) {
                case 0 -> {
                    teamWhiteRose.add(new Monk(1, i+1));
                    teamRedRose.add(new Monk(10, i+1));
                }
                case 1 -> {
                    teamWhiteRose.add(new Magician(1, i+1));
                    teamRedRose.add(new Magician(10, i+1));
                }
                case 2 -> {
                    teamWhiteRose.add(new Peasant(Generator.numberXWhiteRose(), i+1));
                    teamRedRose.add(new Peasant(Generator.numberXRedRose(), i+1));
                }
                case 3 -> {
                    teamWhiteRose.add(new Crossbowman(Generator.numberXWhiteRose(), i+1));
                    teamRedRose.add(new Crossbowman(Generator.numberXRedRose(), i+1));
                }
                case 4 -> {
                    teamWhiteRose.add(new Sniper(Generator.numberXWhiteRose(), i+1));
                    teamRedRose.add(new Sniper(Generator.numberXRedRose(), i+1));
                }
                case 5 -> {
                    teamWhiteRose.add(new Spearman(Generator.numberXWhiteRose(), i+1));
                    teamRedRose.add(new Spearman(Generator.numberXRedRose(), i+1));
                }
                case 6 -> {
                    teamWhiteRose.add(new Robber(Generator.numberXWhiteRose(), i+1));
                    teamRedRose.add(new Robber(Generator.numberXRedRose(), i+1));
                }
            }
        }

        System.out.println("Нечетные игроки (Green Side) - команда WhiteRose, четные (Blue Side)- команда RedRose: ");

        commonList.addAll(teamWhiteRose);
        commonList.addAll(teamRedRose);

        commonList.sort(reverseOrder(comparing(Unit -> Unit.attack)));
        Scanner in = new Scanner(System.in);
        View.view();
        in.nextLine();
        while (true) {
            for (var unit : commonList) {
                if (teamWhiteRose.contains(unit)) unit.step(teamRedRose, teamWhiteRose);
                if (teamRedRose.contains(unit)) unit.step(teamWhiteRose, teamRedRose);
            }

            View.view();
            in.nextLine();
            if (isTeamDead(teamWhiteRose)) {
                System.out.println("Команда RedRose(Blue) выиграла");
                break;
            }

            if (isTeamDead(teamRedRose)) {
                System.out.println("Команда WhiteRose(Green) выиграла");
                break;
            }
        }

    }

}
