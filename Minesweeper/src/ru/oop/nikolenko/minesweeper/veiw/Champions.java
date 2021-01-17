package ru.oop.nikolenko.minesweeper.veiw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Champions {
    private final String[][] championsNames;
    private final Integer[][] championsTimes;
    private final String championsFilePath;
    private final int desiredChampionsCount;

    public Champions(String championsFilePath, int desiredChampionsCount, String[] categoriesNames) {
        championsNames = new String[categoriesNames.length][desiredChampionsCount];
        championsTimes = new Integer[categoriesNames.length][desiredChampionsCount];
        this.championsFilePath = championsFilePath;
        this.desiredChampionsCount = desiredChampionsCount;

        try (Scanner scanner = new Scanner(new FileInputStream(championsFilePath))) {
            for (int i = 0; i < categoriesNames.length; i++) {
                int j = 0;
                int previousTime = -1;

                while (scanner.hasNextLine() && j < desiredChampionsCount) {
                    String s = scanner.nextLine();

                    if (s.isEmpty()) {
                        championsNames[i][j] = null;
                    } else {
                        championsNames[i][j] = s;
                    }

                    s = scanner.nextLine();

                    if (s.isEmpty()) {
                        championsTimes[i][j] = null;
                    } else {
                        championsTimes[i][j] = Integer.parseInt(s);
                    }

                    if (championsTimes[i][j] != null) {
                        if (previousTime <= championsTimes[i][j]) {
                            previousTime = championsTimes[i][j];
                        } else {
                            throw new FileNotFoundException();
                        }
                    }

                    j++;
                }
            }
        } catch (FileNotFoundException | NumberFormatException ignored) {
        }
    }

    public String[][] getChampionsNames() {
        return championsNames;
    }

    public Integer[][] getChampionsTimes() {
        return championsTimes;
    }

    public int getChampionPlace(int categoryNumber, int time) {
        if (championsTimes[categoryNumber][desiredChampionsCount - 1] != null && time >= championsTimes[categoryNumber][desiredChampionsCount - 1]) {
            return -1;
        }

        for (int i = championsTimes[categoryNumber].length - 1; i >= 0; i--) {
            if (championsTimes[categoryNumber][i] != null && time >= championsTimes[categoryNumber][i]) {
                return i + 1;
            }
        }

        return 0;
    }

    public void saveChampion(int categoryNumber, int newChampionTime, String newChampionName, int place) throws FileNotFoundException {
        for (int i = desiredChampionsCount - 1; i > place; i--) {
            championsNames[categoryNumber][i] = championsNames[categoryNumber][i - 1];
            championsTimes[categoryNumber][i] = championsTimes[categoryNumber][i - 1];
        }

        championsNames[categoryNumber][place] = newChampionName;
        championsTimes[categoryNumber][place] = newChampionTime;

        try (PrintWriter writer = new PrintWriter(championsFilePath)) {
            for (int i = 0; i < championsNames.length; i++) {
                for (int j = 0; j < desiredChampionsCount; j++) {
                    if (championsNames[i][j] != null) {
                        writer.println(championsNames[i][j]);
                    } else {
                        writer.println();
                    }

                    if (championsTimes[i][j] != null) {
                        writer.println(championsTimes[i][j]);
                    } else {
                        writer.println();
                    }
                }
            }
        }
    }
}
