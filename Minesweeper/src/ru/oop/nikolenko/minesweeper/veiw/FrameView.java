package ru.oop.nikolenko.minesweeper.veiw;

import ru.oop.nikolenko.minesweeper.controller.MinesweeperController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class FrameView implements View {
    private final MinesweeperController controller;
    private JFrame frame;
    private final MinesweeperLeaders leaders;
    private final MinesweeperOptions minesweeperOptions;
    private final OptionsFrame optionsFrame = new OptionsFrame();
    private final LeaderboardsFrame leaderboardsFrame = new LeaderboardsFrame();
    private final MinesweeperMenuBar menuBar;
    private final AboutView aboutView = new AboutView();

    JLabel timerLabel;
    AtomicInteger secondsCount;
    boolean isFirstCellOpened;
    private Timer timer;
    private JButton mainButton;
    private final MainButtonIcons mainButtonIcons;
    private int remainingMinesCount;
    private JLabel remainingMinesLabel;
    private int currentMode;
    private final int[][] defaultOptionals;
    final String[] categoriesNames;

    private JPanel centrePanel;
    private boolean[][] isOpened;
    private boolean[][] isMarked;
    private final FieldIcons fieldIcons;
    private int notOpenCellCount;
    private int cellsInWidthAmount;
    private int cellsInHeightAmount;
    private int minesAmount;

    public FrameView(MinesweeperController controller, FieldIcons fieldIcons, MainButtonIcons mainButtonIcons,
                     MinesweeperOptions minesweeperOptions, MinesweeperLeaders leaders) {
        this.controller = controller;
        this.fieldIcons = fieldIcons;
        this.mainButtonIcons = mainButtonIcons;
        this.minesweeperOptions = minesweeperOptions;
        this.leaders = leaders;
        this.menuBar = new MenuBar();
        this.defaultOptionals = minesweeperOptions.getDefaultOptions();
        this.categoriesNames = leaders.getCategoriesNames();

        if (categoriesNames.length != defaultOptionals.length) {
            throw new IllegalArgumentException("categoriesNames.length = [" + categoriesNames.length +
                    "] != defaultOptionals.length = [" + defaultOptionals.length + "]");
        }

        int[] optionals = minesweeperOptions.getMimeSweeperOptions();

        if (optionals.length != 4) {
            throw new IllegalArgumentException("optionals.length = " + optionals.length +
                    "must be 4: {cellsInWidthAmount, cellsInHeightAmount, minesCount, currentMode}");
        }

        cellsInWidthAmount = optionals[0];
        cellsInHeightAmount = optionals[1];
        minesAmount = optionals[2];
        currentMode = optionals[3];
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            frame = new JFrame("Minesweeper");

            frame.setJMenuBar(menuBar.getJMenuBar(this));

            GridBagLayout pageStartLayout = new GridBagLayout();
            JPanel pageStartPanel = new JPanel(pageStartLayout);
            frame.add(pageStartPanel, BorderLayout.PAGE_START);

            GridBagConstraints c = new GridBagConstraints();

            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = GridBagConstraints.RELATIVE;
            c.gridy = GridBagConstraints.RELATIVE;
            c.insets = new Insets(5, 15, 5, 15);
            c.ipadx = 0;
            c.ipady = 0;
            c.weightx = 1.0;
            c.weighty = 1.0;

            timerLabel = new JLabel("0");
            pageStartLayout.setConstraints(timerLabel, c);
            pageStartPanel.add(timerLabel);

            secondsCount = new AtomicInteger();
            timer = new Timer(1000, e -> {
                secondsCount.getAndIncrement();

                if (secondsCount.get() < 1000) {
                    timerLabel.setText(String.valueOf(secondsCount));
                }
            });

            mainButton = new JButton(mainButtonIcons.getNormalButtonIcon());
            mainButton.setSize(26, 26);
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.NONE;
            pageStartLayout.setConstraints(mainButton, c);
            pageStartPanel.add(mainButton);

            mainButton.addActionListener(e -> startNewGame());

            remainingMinesLabel = new JLabel();
            remainingMinesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            c.anchor = GridBagConstraints.EAST;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = GridBagConstraints.REMAINDER;
            pageStartLayout.setConstraints(remainingMinesLabel, c);
            pageStartPanel.add(remainingMinesLabel);

            startNewGame();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getWidth()) / 2);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private void setFrameSizes() {
        int fieldIconsWidth = fieldIcons.getIconsWidth();
        int fieldIconsHeight = fieldIcons.getIconsHeight();

        int frameWidth = Math.max(fieldIconsWidth * cellsInWidthAmount, 200);
        int frameHeight = Math.max(fieldIconsHeight, frameWidth / cellsInWidthAmount) * cellsInHeightAmount + 100;
        frame.setSize(frameWidth, frameHeight);
    }

    @Override
    public void startNewGame() {
        controller.startNewGame(cellsInWidthAmount, cellsInHeightAmount, minesAmount);
        isMarked = new boolean[cellsInHeightAmount][cellsInWidthAmount];
        isOpened = new boolean[cellsInHeightAmount][cellsInWidthAmount];
        createField(false);
        setFrameSizes();
        notOpenCellCount = cellsInWidthAmount * cellsInHeightAmount;
        isFirstCellOpened = false;
        remainingMinesCount = minesAmount;
        remainingMinesLabel.setText(String.valueOf(remainingMinesCount));
        timer.stop();
        secondsCount = new AtomicInteger();
        timerLabel.setText(String.valueOf(secondsCount));
        mainButton.setIcon(mainButtonIcons.getNormalButtonIcon());
    }

    @Override
    public void endGame(boolean isWinner) {
        timer.stop();
        createField(true);

        if (isWinner) {
            mainButton.setIcon(mainButtonIcons.getWinnerButtonIcon());

            if (currentMode < defaultOptionals.length) {
                int gamerPlace = leaders.getNewWinnerPlace(currentMode, secondsCount.get());

                if (gamerPlace != -1) {
                    String message = "You are on the leaderboards!" + System.lineSeparator() + "Please enter  your name:";
                    String championName = JOptionPane.showInputDialog(frame, message, "", JOptionPane.PLAIN_MESSAGE);

                    try {
                        leaders.saveLeader(currentMode, secondsCount.get(), championName, gamerPlace);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(frame, "Failed to update the leaderboard", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    openLeaderboardsFrame();
                }
            }
        } else {
            mainButton.setIcon(mainButtonIcons.getLoserButtonIcon());
        }
    }

    private void openCells(int openedCellX, int openedCellY) {
        boolean[][] needBeOpened = controller.openCells(openedCellX, openedCellY, isOpened, isMarked);

        if (needBeOpened != null) {
            isOpened = controller.openCells(openedCellX, openedCellY, isOpened, isMarked);
            notOpenCellCount = cellsInHeightAmount * cellsInWidthAmount;

            for (int i = 0; i < cellsInHeightAmount; i++) {
                for (int j = 0; j < cellsInWidthAmount; j++) {
                    if (isOpened[i][j]) {
                        notOpenCellCount--;
                    }
                }
            }

            if (notOpenCellCount == minesAmount) {
                endGame(true);
            } else {
                createField(false);
            }
        }
    }

    private void createField(boolean isGameEnd) {
        if (centrePanel != null) {
            frame.remove(centrePanel);
        }

        GridLayout centreLayout = new GridLayout(cellsInHeightAmount, cellsInWidthAmount, 0, 0);
        centrePanel = new JPanel(centreLayout);
        frame.add(centrePanel, BorderLayout.CENTER);

        for (int y = 0; y < cellsInHeightAmount; y++) {

            for (int x = 0; x < cellsInWidthAmount; x++) {
                JPanel panelXY = new JPanel(new BorderLayout());
                centrePanel.add(panelXY);

                if (isOpened[y][x]) {
                    panelXY.add(getCellLabel(x, y, isGameEnd));
                } else {
                    panelXY.add(getCellButton(x, y, panelXY, isGameEnd));
                }
            }
        }

        SwingUtilities.updateComponentTreeUI(frame);
    }

    private JButton getCellButton(int x, int y, JPanel panelXY, boolean isGameEnd) {
        JButton buttonXY = new JButton();

        if (isGameEnd) {
            boolean isMine = controller.getTypeOfCell(x, y).equals("mine");

            if (isMarked[y][x]) {
                if (isMine) {
                    buttonXY.setIcon(fieldIcons.getMarkedCellIcon());
                } else {
                    buttonXY.setIcon(fieldIcons.getDefusedMineIcon());
                }
            } else {
                if (isMine) {
                    buttonXY.setIcon(fieldIcons.getMineIcon());
                } else {
                    buttonXY.setIcon(fieldIcons.getEmptyCellIcon());
                }
            }

            return buttonXY;
        } else {
            buttonXY.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 1 && !isMarked[y][x]) {
                        isOpened[y][x] = true;

                        if (!isFirstCellOpened) {
                            timer.start();
                            isFirstCellOpened = true;
                        }


                        String typeOfCell = controller.getTypeOfCell(x, y);

                        if (typeOfCell.equals("mine")) {
                            endGame(false);
                        } else if (typeOfCell.equals("0")) {
                            openCells(x, y);
                        } else {
                            notOpenCellCount--;

                            if (notOpenCellCount == minesAmount) {
                                endGame(true);
                            } else {
                                panelXY.remove(buttonXY);
                                panelXY.add(getCellLabel(x, y, false));
                            }
                        }
                    }

                    if (e.getButton() == 3) {
                        if (!isMarked[y][x]) {
                            buttonXY.setIcon(fieldIcons.getMarkedCellIcon());
                            isMarked[y][x] = true;
                            remainingMinesCount--;
                        } else {
                            buttonXY.setIcon(fieldIcons.getEmptyCellIcon());
                            isMarked[y][x] = false;
                            remainingMinesCount++;
                        }

                        remainingMinesLabel.setText(String.valueOf(remainingMinesCount));
                    }

                    SwingUtilities.updateComponentTreeUI(frame);
                }

                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == 1) {
                        mainButton.setIcon(mainButtonIcons.getCellClickButtonIcon());
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    mainButton.setIcon(mainButtonIcons.getNormalButtonIcon());
                }
            });
        }

        if (isMarked[y][x]) {
            buttonXY.setIcon(fieldIcons.getMarkedCellIcon());
        } else {
            buttonXY.setIcon(fieldIcons.getEmptyCellIcon());
        }

        return buttonXY;
    }

    private JLabel getCellLabel(int x, int y, boolean isGameEnd) {
        JLabel labelXY = new JLabel();
        labelXY.setLayout(new BorderLayout());
        labelXY.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        String s = controller.getTypeOfCell(x, y);

        switch (s) {
            case "mine" -> labelXY.setIcon(fieldIcons.getExplodedMineIcon());
            case "0" -> labelXY.setIcon(fieldIcons.getEmptyIcon());
            case "1" -> labelXY.setIcon(fieldIcons.getNumber1Icon());
            case "2" -> labelXY.setIcon(fieldIcons.getNumber2Icon());
            case "3" -> labelXY.setIcon(fieldIcons.getNumber3Icon());
            case "4" -> labelXY.setIcon(fieldIcons.getNumber4Icon());
            case "5" -> labelXY.setIcon(fieldIcons.getNumber5Icon());
            case "6" -> labelXY.setIcon(fieldIcons.getNumber6Icon());
            case "7" -> labelXY.setIcon(fieldIcons.getNumber7Icon());
            case "8" -> labelXY.setIcon(fieldIcons.getNumber8Icon());
        }

        if (!isGameEnd) {
            labelXY.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == 1) {
                        labelXY.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e) {
                                openCells(x, y);
                            }
                        });
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (e.getButton() == 1) {
                        labelXY.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e) {
                            }
                        });
                    }
                }

            });
        }

        return labelXY;
    }

    public void openLeaderboardsFrame() {
        leaderboardsFrame.openLeaderboardsFrame(leaders.getLeadersNames(), leaders.getLeadersTimes(), categoriesNames);
    }

    public void openOptionalsFrame() {
        optionsFrame.openOptionsFrame(new int[]{cellsInWidthAmount, cellsInHeightAmount, minesAmount}, this, defaultOptionals, categoriesNames);
    }

    public void saveOptions(int[] newOptions, int newMode) {
        try {
            minesweeperOptions.saveOptionals(newOptions);
            cellsInWidthAmount = newOptions[0];
            cellsInHeightAmount = newOptions[1];
            minesAmount = newOptions[2];
            currentMode = newMode;
            startNewGame();
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Failed to update the leaderboard", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void openAboutFrame() {
        final String fileAboutPath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/about.txt";
        aboutView.openAboutFrame(fileAboutPath);
    }
}
