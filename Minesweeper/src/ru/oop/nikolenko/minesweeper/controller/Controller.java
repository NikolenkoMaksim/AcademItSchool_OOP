package ru.oop.nikolenko.minesweeper.controller;

import ru.oop.nikolenko.minesweeper.model.EndTheGame;
import ru.oop.nikolenko.minesweeper.model.MinesweeperModel;
import ru.oop.nikolenko.minesweeper.model.Options;
import ru.oop.nikolenko.minesweeper.view.View;

import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class Controller implements MinesweeperController {
    private final MinesweeperModel model;
    private View view;
    private boolean isFirstCellOpened;

    public Controller(MinesweeperModel model) {
        this.model = model;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void startNewGame() {
        model.createField();
        isFirstCellOpened = false;
        view.setRemainingMinesLabel(model.getRemainingMinesCount());
    }

    @Override
    public String getTypeOfCell(int cellNumberByWidth, int cellNumberByHeight) {
        return model.getTypeOfCell(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public void handleMouseClick(int mouseButton, int cellNumberByWidth, int cellNumberByHeight) {
        if (!isFirstCellOpened && mouseButton == MouseEvent.BUTTON1 && !model.isCellMarked(cellNumberByWidth, cellNumberByHeight)) {
            if (model.getTypeOfCell(cellNumberByWidth, cellNumberByHeight).equals("mine")) {
                model.recreateFieldWithoutMineInCell(cellNumberByWidth, cellNumberByHeight);
            }

            view.startTimer();
            isFirstCellOpened = true;
        }

        EndTheGame endTheGameParameters = model.handleMouseClick(mouseButton, cellNumberByWidth, cellNumberByHeight);

        if (endTheGameParameters.isEndTheGame()) {
            view.endGame(endTheGameParameters.isWin());
        } else {
            view.createField(false);
            view.setRemainingMinesLabel(model.getRemainingMinesCount());
        }
    }

    @Override
    public boolean isCellOpen(int cellNumberByWidth, int cellNumberByHeight) {
        return model.isCellOpen(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public boolean isCellMarked(int cellNumberByWidth, int cellNumberByHeight) {
        return model.isCellMarked(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public Options getCurrentOptions() {
        return model.getCurrentOptions();
    }

    @Override
    public void saveOptions(Options newOptions) throws FileNotFoundException {
        model.saveOptions(newOptions);
    }

    @Override
    public Options[] getDefaultOptions() {
        return model.getDefaultOptions();
    }

    @Override
    public String[][] getLeadersNames() {
        return model.getLeadersNames();
    }

    @Override
    public Integer[][] getLeadersTimes() {
        return model.getLeadersTimes();
    }

    @Override
    public String[] getCategoriesNames() {
        return model.getCategoriesNames();
    }

    @Override
    public int getNewWinnerPlace(int categoryNumber, int time) {
        return model.getNewWinnerPlace(categoryNumber, time);
    }

    @Override
    public void saveLeader(int categoryNumber, int newLeaderTime, String newLeaderName, int place) throws FileNotFoundException {
        model.saveLeader(categoryNumber, newLeaderTime, newLeaderName, place);
    }

    @Override
    public void clearLeaders() throws FileNotFoundException {
        model.clearLeaders();
    }

    @Override
    public StringBuilder getAboutFileStringBuilder() throws FileNotFoundException {
        return model.getAboutFileStringBuilder();
    }

    @Override
    public StringBuilder getRulesFileStringBuilder() throws FileNotFoundException {
        return model.getRulesFileStringBuilder();
    }
}
