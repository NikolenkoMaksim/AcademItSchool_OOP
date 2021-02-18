package ru.oop.nikolenko.minesweeper.controller;

import ru.oop.nikolenko.minesweeper.model.*;
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
    public boolean isMine(int cellNumberByWidth, int cellNumberByHeight) {
        return model.isMine(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public int getMinesAroundCount(int cellNumberByWidth, int cellNumberByHeight) {
        return model.getMinesAroundCount(cellNumberByWidth, cellNumberByHeight);
    }

    @Override
    public void handleMouseClick(int mouseButton, int cellNumberByWidth, int cellNumberByHeight) {
        if (!isFirstCellOpened && mouseButton == MouseEvent.BUTTON1 && !model.isCellMarked(cellNumberByWidth, cellNumberByHeight)) {
            view.startTimer();
            isFirstCellOpened = true;
        }

        if (mouseButton == MouseEvent.BUTTON1) {
            checkEndAndSetView(model.handleFieldEvent(FieldEvent.openClosedCell, cellNumberByWidth, cellNumberByHeight));
            return;
        }

        if (mouseButton == MouseEvent.BUTTON2) {
            checkEndAndSetView(model.handleFieldEvent(FieldEvent.openCellsAroundOpenedCell, cellNumberByWidth, cellNumberByHeight));
            return;
        }

        if (mouseButton == MouseEvent.BUTTON3) {
            checkEndAndSetView(model.handleFieldEvent(FieldEvent.markedCell, cellNumberByWidth, cellNumberByHeight));
        }
    }

    private void checkEndAndSetView(EndGameParameters endGameParametersParameters) {
        if (endGameParametersParameters.isEndTheGame()) {
            view.endGame(endGameParametersParameters.isWin());
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

    public Leader[][] getLeaders() {
        return model.getLeaders();
    }

    @Override
    public String[] getCategoriesNames() {
        return model.getCategoriesNames();
    }

    @Override
    public boolean isLeader(int categoryNumber, int time) {
        return model.isLeader(categoryNumber, time);
    }

    @Override
    public void saveLeader(int categoryNumber, Leader newLeader) throws FileNotFoundException {
        model.saveLeader(categoryNumber, newLeader);
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
