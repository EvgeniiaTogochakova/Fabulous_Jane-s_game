//1.Создать класс с описанием координат, x и y.
//2.Добавить в абстрактный класс поле с координатами и пробросить его инициализацию до конструкторов персонажей.
//Farmer farmer = new Farmer(getName(), x, y);
//3.Найти среди противников ближайшего и вывести расстояние до него и его имя в консоль.

import units.*;

import java.util.ArrayList;
import java.util.Random;

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

        teamWhiteRose.forEach(n -> {
            String[] strings = n.getInfo().split(" ");
            System.out.print("Для " + strings[1] + " ближайший враг и расстояние до него - ");
            n.step(teamRedRose);
        });

    }
}
