package ru.oop.nikolenko.minesweeper.view;

import ru.oop.nikolenko.minesweeper.controller.MinesweeperController;
import ru.oop.nikolenko.minesweeper.model.Leader;
import ru.oop.nikolenko.minesweeper.model.Options;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class FrameView implements View {
    private final MinesweeperController controller;
    private JFrame frame;
    private final FieldIcons fieldIcons;
    private final ImageIcon mainIcon;

    private final MinesweeperMenuBar menuBar = new MenuBar();
    private final OptionsFrame optionsFrame = new OptionsFrame();
    private final LeaderboardsFrame leaderboardsFrame = new LeaderboardsFrame();
    private final AboutFrame aboutFrame = new AboutFrame();
    private final RulesFrame rulesFrame = new RulesFrame();

    private JLabel timerLabel;
    private AtomicInteger secondsCount;
    private Timer timer;
    private JButton mainButton;
    private final MainButtonIcons mainButtonIcons;
    private JLabel remainingMinesLabel;
    private JPanel centrePanel;
    private int cellsInWidthAmount;
    private int cellsInHeightAmount;
    private int currentMode;

    public FrameView(MinesweeperController controller, FieldIcons fieldIcons, MainButtonIcons mainButtonIcons, ImageIcon mainIcon) {
        this.controller = controller;
        this.fieldIcons = fieldIcons;
        this.mainButtonIcons = mainButtonIcons;
        this.mainIcon = mainIcon;

        Options currentOptionals = controller.getCurrentOptions();

        cellsInWidthAmount = currentOptionals.getCellsInWidthAmount();
        cellsInHeightAmount = currentOptionals.getCellsInHeightAmount();
        currentMode = currentOptionals.getNumberOfDefaultOptions();
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            frame = new JFrame("Minesweeper");
            frame.setIconImage(mainIcon.getImage());

            frame.setJMenuBar(menuBar.getJMenuBar(this));

            GridLayout pageStartLayout = new GridLayout(1, 3, 0, 0);
            JPanel pageStartPanel = new JPanel(pageStartLayout);
            pageStartPanel.setBorder(new EmptyBorder(0, 15, 0, 15));
            frame.add(pageStartPanel, BorderLayout.PAGE_START);

            timerLabel = new JLabel("0");
            Font font = new Font("", Font.PLAIN, 17);
            timerLabel.setFont(font);
            pageStartPanel.add(timerLabel);

            secondsCount = new AtomicInteger();
            timer = new Timer(1000, e -> {
                secondsCount.getAndIncrement();

                if (secondsCount.get() < 1000) {
                    timerLabel.setText(String.valueOf(secondsCount));
                }
            });

            JPanel mainButtonPanel = new JPanel(new FlowLayout());
            pageStartPanel.add(mainButtonPanel);

            mainButton = new JButton(mainButtonIcons.getNormalButtonIcon());
            mainButton.setFocusable(false);
            mainButton.setPreferredSize(new Dimension(48, 48));
            mainButtonPanel.add(mainButton);
            mainButton.addActionListener(e -> startNewGame());

            remainingMinesLabel = new JLabel();
            remainingMinesLabel.setFont(font);
            remainingMinesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            pageStartPanel.add(remainingMinesLabel);

            startNewGame();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
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
        controller.startNewGame();
        createField(false);
        setFrameSizes();
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

            if (currentMode > -1) {
                if (controller.isLeader(currentMode, secondsCount.get())) {
                    String message = "You are on the leaderboard!" + System.lineSeparator() + "Please enter  your name:";
                    String leaderName = JOptionPane.showInputDialog(frame, message, "", JOptionPane.PLAIN_MESSAGE);

                    try {
                        controller.saveLeader(currentMode, new Leader(leaderName, secondsCount.get()));
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

    @Override
    public void createField(boolean isGameEnd) {
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

                if (controller.isCellOpen(x, y)) {
                    panelXY.add(getCellLabel(x, y, isGameEnd));
                } else {
                    panelXY.add(getCellButton(x, y, isGameEnd));
                }
            }
        }

        SwingUtilities.updateComponentTreeUI(frame);
    }

    private JButton getCellButton(int x, int y, boolean isGameEnd) {
        JButton buttonXY = new JButton();
        buttonXY.setFocusable(false);

        if (isGameEnd) {
            boolean isMine = controller.isMine(x, y);

            if (controller.isCellMarked(x, y)) {
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
            MouseListener cellReleasedListener = new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    controller.handleMouseClick(e.getButton(), x, y);
                }
            };

            buttonXY.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == 1) {
                        mainButton.setIcon(mainButtonIcons.getCellClickButtonIcon());
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    mainButton.setIcon(mainButtonIcons.getNormalButtonIcon());
                }

                public void mouseEntered(MouseEvent e) {
                    buttonXY.addMouseListener(cellReleasedListener);
                }

                public void mouseExited(MouseEvent e) {
                    buttonXY.removeMouseListener(cellReleasedListener);
                }
            });
        }

        if (controller.isCellMarked(x, y)) {
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

        if (controller.isMine(x, y)) {
            labelXY.setIcon(fieldIcons.getExplodedMineIcon());
        } else {
            switch (controller.getMinesAroundCount(x, y)) {
                case 0 -> labelXY.setIcon(fieldIcons.getEmptyIcon());
                case 1 -> labelXY.setIcon(fieldIcons.getNumber1Icon());
                case 2 -> labelXY.setIcon(fieldIcons.getNumber2Icon());
                case 3 -> labelXY.setIcon(fieldIcons.getNumber3Icon());
                case 4 -> labelXY.setIcon(fieldIcons.getNumber4Icon());
                case 5 -> labelXY.setIcon(fieldIcons.getNumber5Icon());
                case 6 -> labelXY.setIcon(fieldIcons.getNumber6Icon());
                case 7 -> labelXY.setIcon(fieldIcons.getNumber7Icon());
                case 8 -> labelXY.setIcon(fieldIcons.getNumber8Icon());
            }
        }

        MouseListener waitingRightMousePressListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    controller.handleMouseClick(MouseEvent.BUTTON2, x, y);
                }
            }
        };

        MouseListener waitingLeftMousePressListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    controller.handleMouseClick(MouseEvent.BUTTON2, x, y);
                }
            }
        };

        MouseListener firstButtonPressedListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    labelXY.addMouseListener(waitingRightMousePressListener);
                }

                if (e.getButton() == 3) {
                    labelXY.addMouseListener(waitingLeftMousePressListener);
                }

                if (e.getButton() == 2) {
                    controller.handleMouseClick(MouseEvent.BUTTON2, x, y);
                }
            }

            public void mouseReleased(MouseEvent e) {
                labelXY.removeMouseListener(waitingRightMousePressListener);
                labelXY.removeMouseListener(waitingLeftMousePressListener);
            }
        };

        if (!isGameEnd) {
            labelXY.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    labelXY.addMouseListener(firstButtonPressedListener);
                }

                public void mouseExited(MouseEvent e) {
                    labelXY.removeMouseListener(firstButtonPressedListener);
                    labelXY.removeMouseListener(waitingRightMousePressListener);
                }
            });
        }

        return labelXY;
    }

    public void openLeaderboardsFrame() {
        leaderboardsFrame.openLeaderboardsFrame(controller.getLeaders(), controller.getCategoriesNames(), this);
    }

    public void clearLeaderboard() {
        try {
            controller.clearLeaders();
            openLeaderboardsFrame();
        } catch (FileNotFoundException e) {
            openErrorMessage("Failed to updateLeaderboard");
        }
    }

    public void openOptionalsFrame() {
        optionsFrame.openOptionsFrame(controller.getCurrentOptions(), controller.getDefaultOptions(), this);
    }

    public void saveOptions(Options newOptions) {
        try {
            controller.saveOptions(newOptions);
            cellsInWidthAmount = newOptions.getCellsInWidthAmount();
            cellsInHeightAmount = newOptions.getCellsInHeightAmount();
            currentMode = newOptions.getNumberOfDefaultOptions();
            startNewGame();
        } catch (FileNotFoundException e) {
            openErrorMessage("Failed to save options");
        }
    }

    public void openRulesFrame() {
        try {
            rulesFrame.openRulesFrame(controller.getRulesFileStringBuilder());
        } catch (FileNotFoundException e) {
            openErrorMessage("There is no data");
        }
    }

    public void openAboutFrame() {
        try {
            aboutFrame.openAboutFrame(controller.getAboutFileStringBuilder());
        } catch (FileNotFoundException e) {
            openErrorMessage("There is no data");
        }
    }

    private void openErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void startTimer() {
        timer.start();
    }

    @Override
    public void setRemainingMinesLabel(int remainingMinesCount) {
        remainingMinesLabel.setText(String.valueOf(remainingMinesCount));
    }
}
