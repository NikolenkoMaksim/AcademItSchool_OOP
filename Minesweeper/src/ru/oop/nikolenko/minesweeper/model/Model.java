package ru.oop.nikolenko.minesweeper.model;

import java.io.FileNotFoundException;

public class Model implements MinesweeperModel {
    private final MinesweeperField minesweeperField;
    private final MinesweeperOptions minesweeperOptions;
    private final MinesweeperLeaders minesweeperLeaders;
    private final FileToStringBuilderConverter fileToStringBuilderConverter;
    private final String aboutFilePath;
    private final String rulesFilePath;

    public Model(MinesweeperField minesweeperField, MinesweeperOptions minesweeperOptions, MinesweeperLeaders minesweeperLeaders,
                 FileToStringBuilderConverter fileToStringBuilderConverter, String aboutFilePath, String rulesFilePath) {
        this.minesweeperField = minesweeperField;
        this.minesweeperOptions = minesweeperOptions;
        this.minesweeperLeaders = minesweeperLeaders;
        this.fileToStringBuilderConverter = fileToStringBuilderConverter;
        this.aboutFilePath = aboutFilePath;
        this.rulesFilePath = rulesFilePath;
    }

    @Override
    public String[][] getField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount) {
        return minesweeperField.getField(cellsInWidthAmount, cellsInHeightAmount, minesAmount);
    }

    @Override
    public String[][] getField(String[][] minesField) {
        return minesweeperField.getField(minesField);
    }

    @Override
    public String[][] getMinesField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount) {
        return minesweeperField.getMinesField(cellsInWidthAmount, cellsInHeightAmount, minesAmount);
    }

    @Override
    public int[] getMinesweeperOptions() {
        return minesweeperOptions.getMinesweeperOptions();
    }

    @Override
    public void saveOptions(int[] minesweeperOptions) throws FileNotFoundException {
        this.minesweeperOptions.saveOptions(minesweeperOptions);
    }

    @Override
    public int[][] getDefaultOptions() {
        return minesweeperOptions.getDefaultOptions();
    }

    @Override
    public String[][] getLeadersNames() {
        return minesweeperLeaders.getLeadersNames();
    }

    @Override
    public Integer[][] getLeadersTimes() {
        return minesweeperLeaders.getLeadersTimes();
    }

    @Override
    public String[] getCategoriesNames() {
        return minesweeperLeaders.getCategoriesNames();
    }

    @Override
    public int getNewWinnerPlace(int categoryNumber, int time) {
        return minesweeperLeaders.getNewWinnerPlace(categoryNumber, time);
    }

    @Override
    public void saveLeader(int categoryNumber, int newLeaderTime, String newLeaderName, int place) throws FileNotFoundException {
        minesweeperLeaders.saveLeader(categoryNumber, newLeaderTime, newLeaderName, place);
    }

    @Override
    public void clearLeaders() throws FileNotFoundException {
        minesweeperLeaders.clearLeaders();
    }

    @Override
    public StringBuilder getAboutFileStringBuilder() throws FileNotFoundException {
        return fileToStringBuilderConverter.convertDataToStringBuilder(aboutFilePath);
    }

    @Override
    public StringBuilder getRulesFileStringBuilder() throws FileNotFoundException {
        return fileToStringBuilderConverter.convertDataToStringBuilder(rulesFilePath);
    }
}