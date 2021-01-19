package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;

public interface FieldIcons {
    Icon getMarkedCellIcon();

    Icon getEmptyCellIcon();

    Icon getMineIcon();

    Icon getExplodedMineIcon();

    Icon getDefusedMineIcon();

    Icon getEmptyIcon();

    Icon getNumber1Icon();

    Icon getNumber2Icon();

    Icon getNumber3Icon();

    Icon getNumber4Icon();

    Icon getNumber5Icon();

    Icon getNumber6Icon();

    Icon getNumber7Icon();

    Icon getNumber8Icon();

    int getIconsWidth();

    int getIconsHeight();
}
