package ru.oop.nikolenko.minesweeper.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Leaders implements MinesweeperLeaders {
    private Leader[][] leaders;
    private final String leadersFilePath;
    private final String[] categoriesNames;

    public Leaders(String leadersFilePath, int leadersCountInCategory, String[] categoriesNames) {
        if (categoriesNames.length < 1) {
            throw new IllegalArgumentException("categoriesNames.length = " + categoriesNames.length + " can't be < 1");
        }

        for (int i = 0; i < categoriesNames.length; i++) {
            if (categoriesNames[i] == null) {
                throw new IllegalArgumentException("categoriesNames[" + i + "] can't be null");
            }
        }

        if (leadersCountInCategory < 0) {
            throw new IllegalArgumentException("leadersCountInCategory = " + leadersCountInCategory + "can't be < 0");
        }

        leaders = new Leader[categoriesNames.length][leadersCountInCategory];
        this.leadersFilePath = leadersFilePath;
        this.categoriesNames = categoriesNames;

        try (Scanner scanner = new Scanner(new FileInputStream(leadersFilePath))) {
            for (int i = 0; i < categoriesNames.length; i++) {
                int j = 0;
                int previousTime = -1;

                while (scanner.hasNextLine() && j < leadersCountInCategory) {
                    String leaderName = null;
                    Integer leaderTime = null;

                    String s = scanner.nextLine();

                    if (!s.isEmpty()) {
                        leaderName = s;
                    }

                    s = scanner.nextLine();

                    if (!s.isEmpty()) {
                        leaderTime = Integer.parseInt(s);
                    }

                    if (leaderTime != null) {
                        if (previousTime <= leaderTime) {
                            previousTime = leaderTime;
                        } else {
                            throw new IllegalArgumentException("previousTime (= " + previousTime + ") > leaderTime (" + leaderTime + ")");
                        }

                        if (leaderName != null) {
                            leaders[i][j] = new Leader(leaderName, leaderTime);
                        } else {
                            leaders[i][j] = new Leader(leaderTime);
                        }
                    }

                    j++;
                }
            }
        } catch (FileNotFoundException | NoSuchElementException | IllegalArgumentException ignored) {
        }
    }

    @Override
    public Leader[][] getLeaders() {
        return leaders;
    }

    @Override
    public String[] getCategoriesNames() {
        return categoriesNames;
    }

    @Override
    public boolean isLeader(int categoryNumber, int time) {
        checkCategoryNumber(categoryNumber);
        checkTime(time);

        return getNewWinnerPlace(categoryNumber, time) > -1;
    }

    private void checkCategoryNumber(int categoryNumber) {
        if (categoryNumber < 0) {
            throw new IllegalArgumentException("categoryNumber = " + categoryNumber + " can't be < 0");
        }

        if (categoryNumber > categoriesNames.length) {
            throw new IllegalArgumentException("categoryNumber = " + categoryNumber + " can't be > categoriesNames.length = " + categoriesNames.length);
        }
    }

    private void checkTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("time = " + time + " can't be < 0");
        }
    }

    private int getNewWinnerPlace(int categoryNumber, int time) {
        if (time < 0) {
            throw new IllegalArgumentException("time = " + time + " can't be < 0");
        }

        if (leaders[categoryNumber][leaders[categoryNumber].length - 1] != null && time >= leaders[categoryNumber][leaders[categoryNumber].length - 1].getTime()) {
            return -1;
        }

        for (int i = leaders[categoryNumber].length - 2; i >= 0; i--) {
            if (leaders[categoryNumber][i] != null && time >= leaders[categoryNumber][i].getTime()) {
                return i + 1;
            }
        }

        return 0;
    }

    @Override
    public void saveLeader(int categoryNumber, Leader newLeader) throws FileNotFoundException {
        checkCategoryNumber(categoryNumber);

        int place = getNewWinnerPlace(categoryNumber, newLeader.getTime());

        if (place > -1) {
            if (leaders[categoryNumber].length - 1 - place >= 0) {
                System.arraycopy(leaders[categoryNumber], place, leaders[categoryNumber], place + 1, leaders[categoryNumber].length - 1 - place);
            }
        }

        leaders[categoryNumber][place] = newLeader;

        try (PrintWriter writer = new PrintWriter(leadersFilePath)) {
            for (Leader[] leader : leaders) {
                for (int j = 0; j < leaders[0].length; j++) {
                    if (leader[j] != null) {
                        writer.println(leader[j].getName());
                        writer.println(leader[j].getTime());
                    } else {
                        writer.println();
                        writer.println();
                    }
                }
            }
        }
    }

    @Override
    public void clearLeaders() throws FileNotFoundException {
        leaders = new Leader[categoriesNames.length][leaders[0].length];

        try (PrintWriter writer = new PrintWriter(leadersFilePath)) {
            writer.println();
        }
    }
}