// Добавить файл с описанием интерфейса.
// В котором описать два метода, void step(); и String getInfo();
// Реализовать интерфейс в абстрактном классе и в наследниках так, чтобы getInfo возвращал тип персонажа.
// Создать два списка в классе main.
// В каждый из списков добавить по десять случайных экземнляров наследников BaseHero.
// Удалить ненужные методы из абстрактного класса, если такие есть.
// В main пройти по спискам и вызвать у всех персонажей getInfo.

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
                    teamWhiteRose.add(new Monk());
                    teamRedRose.add(new Monk());
                }
                case 1 -> {
                    teamWhiteRose.add(new Magician());
                    teamRedRose.add(new Magician());
                }
                case 2 -> {
                    teamWhiteRose.add(new Peasant());
                    teamRedRose.add(new Peasant());
                }
                case 3 -> {
                    teamWhiteRose.add(new Crossbowman());
                    teamRedRose.add(new Crossbowman());
                }
                case 4 -> {
                    teamWhiteRose.add(new Sniper());
                    teamRedRose.add(new Sniper());
                }
                case 5 -> {
                    teamWhiteRose.add(new Spearman());
                    teamRedRose.add(new Spearman());
                }
                case 6 -> {
                    teamWhiteRose.add(new Robber());
                    teamRedRose.add(new Robber());
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

        if ((archers > 0)&&(peasants == 0)) {
            teamWhiteRose.add(new Peasant());
            teamRedRose.add(new Peasant());
            System.out.println("Пришлось добавить по одному крестьянину в команды сверх нормы");
        }

        for (int i = 0; i < teamWhiteRose.size(); i++) {
            System.out.println(teamWhiteRose.get(i).getInfo());
            System.out.println(teamRedRose.get(i).getInfo());
        }

        System.out.println("Общее количество игроков в каждой из команд в итоге составило " + teamWhiteRose.size());
    }
}
