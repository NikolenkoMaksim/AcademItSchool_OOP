package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;

public class FieldStandardIcons implements FieldIcons {
    private final Icon markedCellIcon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/flag1.png");
    private final Icon emptyCellIcon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/flag0.png");
    private final Icon mineIcon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/mine.png");
    private final Icon explodedMineIcon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/explodedMine.png");
    private final Icon defusedMineIcon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/defusedMine.png");
    private final Icon emptyIcon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/0.png");
    private final Icon number1Icon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/1.png");
    private final Icon number2Icon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/2.png");
    private final Icon number3Icon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/3.png");
    private final Icon number4Icon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/4.png");
    private final Icon number5Icon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/5.png");
    private final Icon number6Icon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/6.png");
    private final Icon number7Icon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/7.png");
    private final Icon number8Icon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/8.png");

    @Override
    public Icon getMarkedCellIcon() {
        return markedCellIcon;
    }

    @Override
    public Icon getEmptyCellIcon() {
        return emptyCellIcon;
    }

    @Override
    public Icon getMineIcon() {
        return mineIcon;
    }

    @Override
    public Icon getExplodedMineIcon() {
        return explodedMineIcon;
    }

    @Override
    public Icon getDefusedMineIcon() {
        return defusedMineIcon;
    }

    @Override
    public Icon getEmptyIcon() {
        return emptyIcon;
    }

    @Override
    public Icon getNumber1Icon() {
        return number1Icon;
    }

    @Override
    public Icon getNumber2Icon() {
        return number2Icon;
    }

    @Override
    public Icon getNumber3Icon() {
        return number3Icon;
    }

    @Override
    public Icon getNumber4Icon() {
        return number4Icon;
    }

    @Override
    public Icon getNumber5Icon() {
        return number5Icon;
    }

    @Override
    public Icon getNumber6Icon() {
        return number6Icon;
    }

    @Override
    public Icon getNumber7Icon() {
        return number7Icon;
    }

    @Override
    public Icon getNumber8Icon() {
        return number8Icon;
    }

    @Override
    public int getIconsWidth() {
        return 16;
    }

    @Override
    public int getIconsHeight() {
        return 16;
    }
}
