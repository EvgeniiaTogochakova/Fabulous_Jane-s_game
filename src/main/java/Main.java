// Step лучников
//1.Если жизни 0 вернуть управление
//2.Если стрел 0 вернуть управление
//3.Найти ближайшего противника
//4.Нанести ему среднее повреждение
//5.Если среди своих есть крестьянин вернуть управление
//6.уменьшить кол-во стрел на одну и вернуть управление
//Вызывать персонажей из обеих команд в порядке инициативы.


//В моей интерпретации игры я использую вместо "инициативы" другое слово - "атакующие силы". У лучников это называется
//силами для стрельбы, у копейщика - силы для удара копьем, у разбойника - силы для атаки, у мага - чары для ослабления,
//у монаха силы для распыления яда. В моей интерпретации игры при атаке не только жертва теряет Hp, но и атакующие силы
//у атакующего быстро истощаются, со временем, будучи еще живым, атаковать он не сможет. Потом посмотрю, хорошая ли это практика.
//Специально под лучников сделала метод getDamageByArcher(), где как раз-таки среднее арифметическое считается и
//не учитывается наличие брони. Т.к. такое домашнее задание.
//У меня есть getDamage() для общего случая, но он пока совсем другой.
//Сначала вывожу общий состав команд по порядку, потом делаю step, сортируя по объему атакующих сил, т.е. инициативы.



import units.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class Main {
    public static void main(String[] args) {
        ArrayList<Unit> teamWhiteRose = new ArrayList<>();
        ArrayList<Unit> teamRedRose = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            int digit = new Random().nextInt(7);
            switch (digit) {
                case 0 -> {
                    teamWhiteRose.add(new Monk(new Random().nextInt(0, 6), new Random().nextInt(51)));
                    teamRedRose.add(new Monk(new Random().nextInt(20, 26), new Random().nextInt(51)));
                }
                case 1 -> {
                    teamWhiteRose.add(new Magician(new Random().nextInt(0, 6), new Random().nextInt(51)));
                    teamRedRose.add(new Magician(new Random().nextInt(20, 26), new Random().nextInt(51)));
                }
                case 2 -> {
                    teamWhiteRose.add(new Peasant(new Random().nextInt(0, 6), new Random().nextInt(51)));
                    teamRedRose.add(new Peasant(new Random().nextInt(20, 26), new Random().nextInt(51)));
                }
                case 3 -> {
                    teamWhiteRose.add(new Crossbowman(new Random().nextInt(0, 6), new Random().nextInt(51)));
                    teamRedRose.add(new Crossbowman(new Random().nextInt(20, 26), new Random().nextInt(51)));
                }
                case 4 -> {
                    teamWhiteRose.add(new Sniper(new Random().nextInt(0, 6), new Random().nextInt(51)));
                    teamRedRose.add(new Sniper(new Random().nextInt(20, 26), new Random().nextInt(51)));
                }
                case 5 -> {
                    teamWhiteRose.add(new Spearman(new Random().nextInt(0, 6), new Random().nextInt(51)));
                    teamRedRose.add(new Spearman(new Random().nextInt(20, 26), new Random().nextInt(51)));
                }
                case 6 -> {
                    teamWhiteRose.add(new Robber(new Random().nextInt(0, 6), new Random().nextInt(51)));
                    teamRedRose.add(new Robber(new Random().nextInt(20, 26), new Random().nextInt(51)));
                }
            }
        }

        int archers = 0;
        int peasants = 0;

        // чтобы не оказалось так, что лучники есть в командах, а крестьян им в помощь нет.
        // в таком случае придется добавить по крестьянину в команды (по одиннадцатому игроку)

        for (int i = 0; i < teamWhiteRose.size(); i++) {
            if (teamWhiteRose.get(i) instanceof Crossbowman) {
                archers++;
            }
            if (teamWhiteRose.get(i) instanceof Peasant) {
                peasants++;
            }
        }

        System.out.println("Лучников по " + archers + " в каждой из команд");
        System.out.println("Крестьян по " + peasants + " в каждой из команд");

        if ((archers > 0) && (peasants == 0)) {
            teamWhiteRose.add(new Peasant(new Random().nextInt(0, 6), new Random().nextInt(51)));
            teamRedRose.add(new Peasant(new Random().nextInt(20, 26), new Random().nextInt(51)));
            System.out.println("Пришлось добавить по одному крестьянину в команды сверх нормы");
        }

        for (int i = 0; i < teamWhiteRose.size(); i++) {
            System.out.println(teamWhiteRose.get(i).getInfo());
            System.out.println(teamRedRose.get(i).getInfo());
        }

        System.out.println("Общее количество игроков в каждой из команд в итоге составило " + teamWhiteRose.size());

        ArrayList<Unit> commonList = new ArrayList<>(teamWhiteRose);
        commonList.addAll(teamRedRose);

        commonList.sort(reverseOrder(comparing(Unit -> Unit.attack)));
        System.out.println("--------------------------------------------------------------");
        System.out.println("Вывожу step персонажей в порядке убывания объема атакующих сил");
        System.out.println("--------------------------------------------------------------");
        commonList.forEach(n -> {
            String[] strings = n.getInfo().split(" ");
            System.out.print("Для " + strings[1] + " ближайший враг и расстояние до него - ");
            if (teamWhiteRose.contains(n)) n.step(teamRedRose,teamWhiteRose);
            if (teamRedRose.contains(n)) n.step(teamWhiteRose,teamRedRose);
        });



    }
}
