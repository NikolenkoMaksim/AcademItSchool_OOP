package ru.oop.nikolenko.minesweeper.veiw;

import ru.oop.nikolenko.minesweeper.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class FrameView implements View {
    private final Controller controller;
    private JFrame frame;
    private final String[] categoriesNames;
    private final Champions champions;
    private int[] mineSweeperOptionals;
    private final String optionalsFilePath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/optionals.txt";

    JLabel timerLabel;
    AtomicInteger secondsCount;
    boolean isFirstCellOpened;
    private Timer timer;
    private JButton smileButton;
    private final SmileButtonIcons smileIcons = new SmileButtonIcons();
    private int remainingMinesCount;
    private JLabel remainingMinesLabel;

    private JPanel centrePanel;
    private boolean[][] isOpened;
    private boolean[][] isMarked;
    private final FieldIcons fieldIcons = new FieldIcons();
    private int notOpenCellCount;
    private int fieldWidth;
    private int fieldHeight;
    private int minesCount;

    public FrameView(Controller controller) {
        this.controller = controller;

        categoriesNames = new String[]{"Beginner", "Amateur", "Professional"};
        String championsFilePath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/champions.txt";
        champions = new Champions(championsFilePath, 5, categoriesNames);

        mineSweeperOptionals = MineSweeperOptions.getMimeSweeperOptions(optionalsFilePath);
        fieldWidth = mineSweeperOptionals[0];
        fieldHeight = mineSweeperOptionals[1];
        minesCount = mineSweeperOptionals[2];
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            frame = new JFrame("Minesweeper");

            JMenuBar menuBar = MinesweeperMenuBar.createJMenuBar(this);

            frame.setJMenuBar(menuBar);

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

            smileButton = new JButton(smileIcons.getNormalFace());
            smileButton.setSize(26, 26);
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.NONE;
            pageStartLayout.setConstraints(smileButton, c);
            pageStartPanel.add(smileButton);

            smileButton.addActionListener(e -> startNewGame());

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
        int frameWidth = Math.max(16 * fieldWidth, 200);
        int frameHeight = Math.max(16, frameWidth / fieldWidth) * fieldHeight + 100;
        frame.setSize(frameWidth, frameHeight);
    }

    public void startNewGame() {
        controller.startNewGame(fieldWidth, fieldHeight, minesCount);
        isMarked = new boolean[fieldHeight][fieldWidth];
        isOpened = new boolean[fieldHeight][fieldWidth];
        createField(false);
        setFrameSizes();
        notOpenCellCount = fieldWidth * fieldHeight;
        isFirstCellOpened = false;
        remainingMinesCount = minesCount;
        remainingMinesLabel.setText(String.valueOf(remainingMinesCount));
        timer.stop();
        secondsCount = new AtomicInteger();
        timerLabel.setText(String.valueOf(secondsCount));
        smileButton.setIcon(smileIcons.getNormalFace());
    }

    public void endGame(boolean isWinner) {
        timer.stop();
        createField(true);

        if (isWinner) {
            smileButton.setIcon(smileIcons.getWinnerFace());
            int gamerPlace = champions.getChampionPlace(0, secondsCount.get());

            if (gamerPlace != -1) {
                String message = "You are on the leaderboards!" + System.lineSeparator() + "Please enter  your name:";
                String championName = JOptionPane.showInputDialog(frame, message, "", JOptionPane.PLAIN_MESSAGE);

                try {
                    champions.saveChampion(0, secondsCount.get(), championName, gamerPlace);
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(frame, "Failed to update the leaderboard", "Error", JOptionPane.ERROR_MESSAGE);
                }

                openLeaderboardsFrame();
            }
        } else {
            smileButton.setIcon(smileIcons.getLoserFace());
        }
    }


    private void openCells(int openedCellX, int openedCellY) {
        boolean[][] needBeOpened = controller.openCells(openedCellX, openedCellY, isOpened, isMarked);

        if (needBeOpened != null) {
            isOpened = controller.openCells(openedCellX, openedCellY, isOpened, isMarked);
            notOpenCellCount = fieldHeight * fieldWidth;

            for (int i = 0; i < fieldHeight; i++) {
                for (int j = 0; j < fieldWidth; j++) {
                    if (isOpened[i][j]) {
                        notOpenCellCount--;
                    }
                }
            }

            if (notOpenCellCount == minesCount) {
                endGame(true);
            } else {
                createField(false);
            }
        }
    }

    public void createField(boolean isGameEnd) {
        if (centrePanel != null) {
            frame.remove(centrePanel);
        }

        GridLayout centreLayout = new GridLayout(fieldHeight, fieldWidth, 0, 0);
        centrePanel = new JPanel(centreLayout);
        frame.add(centrePanel, BorderLayout.CENTER);

        for (int y = 0; y < fieldHeight; y++) {

            for (int x = 0; x < fieldWidth; x++) {
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

                            if (notOpenCellCount == minesCount) {
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
                        smileButton.setIcon(smileIcons.getAnxiousFace());
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    smileButton.setIcon(smileIcons.getNormalFace());
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
        LeaderboardsView.createLeaderboardsView(champions.getChampionsNames(), champions.getChampionsTimes(), categoriesNames);
    }

    public void openOptionalsFrame() {
        OptionalsView.createOptionalsView(mineSweeperOptionals, this);
    }

    public void saveOptions(int[] newOptionals) {
        try {
            MineSweeperOptions.saveOptionals(optionalsFilePath, newOptionals);
            mineSweeperOptionals = newOptionals;
            fieldWidth = mineSweeperOptionals[0];
            fieldHeight = mineSweeperOptionals[1];
            minesCount = mineSweeperOptionals[2];
            startNewGame();
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Failed to update the leaderboard", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void openAboutFrame() {
        final String fileAboutPath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/about.txt";
        AboutView.createAboutFrame(fileAboutPath);
    }
}
