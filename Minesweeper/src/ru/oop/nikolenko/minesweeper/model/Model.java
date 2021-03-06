package ru.oop.nikolenko.minesweeper.model;

import java.io.FileNotFoundException;

public class Model implements MinesweeperModel {
    private final MinesweeperField minesweeperField;
    private final WorkWithMinesweeperOptions workWithMinesweeperOptions;
    private final MinesweeperLeaders minesweeperLeaders;
    private final FileToStringBuilderConverter fileToStringBuilderConverter;
    private final String aboutFilePath;
    private final String rulesFilePath;

    public Model(MinesweeperField minesweeperField, WorkWithMinesweeperOptions workWithMinesweeperOptions, MinesweeperLeaders minesweeperLeaders,
                 FileToStringBuilderConverter fileToStringBuilderConverter, String aboutFilePath, String rulesFilePath) {

        if (minesweeperLeaders.getCategoriesNames().length != workWithMinesweeperOptions.getDefaultOptions().length) {
            throw new IllegalArgumentException("categoriesNames.length = [" + minesweeperLeaders.getCategoriesNames().length +
                    "] of MinesweeperLeaders != defaultOptionals.length = [" + workWithMinesweeperOptions.getDefaultOptions().length + "] of WorkWithMinesweeperOptions");
        }

        this.minesweeperField = minesweeperField;
        this.workWithMinesweeperOptions = workWithMinesweeperOptions;
        this.minesweeperLeaders = minesweeperLeaders;
        this.fileToStringBuilderConverter = fileToStringBuilderConverter;
        this.aboutFilePath = aboutFilePath;
        this.rulesFilePath = rulesFilePath;
    }

    @Override
    public void createField() {
        minesweeperField.createField(workWithMinesweeperOptions.getCurrentOptions());
    }

    @Override
    public EndGameParameters recreateFieldWithoutMineInCell(int cellNumberByWidth, int cellNumberByHeight) {
        return minesweeperField.recreateFieldWithoutMineInCell(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public boolean isMine(int cellNumberByWidth, int cellNumberByHeight) {
        return minesweeperField.isMine(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public int getMinesAroundCount(int cellNumberByWidth, int cellNumberByHeight) {
        return minesweeperField.getMinesAroundCount(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public EndGameParameters handleFieldEvent(FieldEvent fieldEvent, int cellNumberByWidth, int cellNumberByHeight) {
        return minesweeperField.handleFieldEvent(fieldEvent, cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public boolean isCellOpen(int cellNumberByWidth, int cellNumberByHeight) {
        return minesweeperField.isCellOpen(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public boolean isCellMarked(int cellNumberByWidth, int cellNumberByHeight) {
        return minesweeperField.isCellMarked(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public int getRemainingMinesCount() {
        return minesweeperField.getRemainingMinesCount();
    }

    @Override
    public Options getCurrentOptions() {
        return workWithMinesweeperOptions.getCurrentOptions();
    }

    @Override
    public void saveOptions(Options newOptions) throws FileNotFoundException {
        this.workWithMinesweeperOptions.saveOptions(newOptions);
    }

    @Override
    public Options[] getDefaultOptions() {
        return workWithMinesweeperOptions.getDefaultOptions();
    }

    @Override
    public Leader[][] getLeaders() {
        return minesweeperLeaders.getLeaders();
    }

    @Override
    public String[] getCategoriesNames() {
        return minesweeperLeaders.getCategoriesNames();
    }

    @Override
    public boolean isLeader(int categoryNumber, int time) {
        return minesweeperLeaders.isLeader(categoryNumber, time);
    }

    @Override
    public void saveLeader(int categoryNumber, Leader newLeader) throws FileNotFoundException {
        minesweeperLeaders.saveLeader(categoryNumber, newLeader);
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