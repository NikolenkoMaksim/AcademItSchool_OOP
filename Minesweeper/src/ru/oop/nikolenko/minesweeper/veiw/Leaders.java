package ru.oop.nikolenko.minesweeper.veiw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Leaders implements MinesweeperLeaders {
    private final String[][] leadersNames;
    private final Integer[][] leadersTimes;
    private final String leadersFilePath;
    private final int desiredLeadersCountInCategory;
    private final String[] categoriesNames;

    public Leaders(String leadersFilePath, int desiredLeadersCountInCategory, String[] categoriesNames) {
        leadersNames = new String[categoriesNames.length][desiredLeadersCountInCategory];
        leadersTimes = new Integer[categoriesNames.length][desiredLeadersCountInCategory];
        this.leadersFilePath = leadersFilePath;
        this.desiredLeadersCountInCategory = desiredLeadersCountInCategory;
        this.categoriesNames = categoriesNames;

        try (Scanner scanner = new Scanner(new FileInputStream(leadersFilePath))) {
            for (int i = 0; i < categoriesNames.length; i++) {
                int j = 0;
                int previousTime = -1;

                while (scanner.hasNextLine() && j < desiredLeadersCountInCategory) {
                    String s = scanner.nextLine();

                    if (s.isEmpty()) {
                        leadersNames[i][j] = null;
                    } else {
                        leadersNames[i][j] = s;
                    }

                    s = scanner.nextLine();

                    if (s.isEmpty()) {
                        leadersTimes[i][j] = null;
                    } else {
                        leadersTimes[i][j] = Integer.parseInt(s);
                    }

                    if (leadersTimes[i][j] != null) {
                        if (previousTime <= leadersTimes[i][j]) {
                            previousTime = leadersTimes[i][j];
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

    public String[][] getLeadersNames() {
        return leadersNames;
    }

    public Integer[][] getLeadersTimes() {
        return leadersTimes;
    }

    public String[] getCategoriesNames() {
        return categoriesNames;
    }

    public int getNewWinnerPlace(int categoryNumber, int time) {
        if (leadersTimes[categoryNumber][desiredLeadersCountInCategory - 1] != null && time >= leadersTimes[categoryNumber][desiredLeadersCountInCategory - 1]) {
            return -1;
        }

        for (int i = leadersTimes[categoryNumber].length - 1; i >= 0; i--) {
            if (leadersTimes[categoryNumber][i] != null && time >= leadersTimes[categoryNumber][i]) {
                return i + 1;
            }
        }

        return 0;
    }

    public void saveLeader(int categoryNumber, int newLeaderTime, String newLeaderName, int place) throws FileNotFoundException {
        for (int i = desiredLeadersCountInCategory - 1; i > place; i--) {
            leadersNames[categoryNumber][i] = leadersNames[categoryNumber][i - 1];
            leadersTimes[categoryNumber][i] = leadersTimes[categoryNumber][i - 1];
        }

        leadersNames[categoryNumber][place] = newLeaderName;
        leadersTimes[categoryNumber][place] = newLeaderTime;

        try (PrintWriter writer = new PrintWriter(leadersFilePath)) {
            for (int i = 0; i < leadersNames.length; i++) {
                for (int j = 0; j < desiredLeadersCountInCategory; j++) {
                    if (leadersNames[i][j] != null) {
                        writer.println(leadersNames[i][j]);
                    } else {
                        writer.println();
                    }

                    if (leadersTimes[i][j] != null) {
                        writer.println(leadersTimes[i][j]);
                    } else {
                        writer.println();
                    }
                }
            }
        }
    }
}
