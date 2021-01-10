package ru.oop.nikolenko.minesweeper.veiw;

import ru.oop.nikolenko.minesweeper.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameView implements View {
    private final Controller controller;
    private JFrame frame;
    private JPanel centrePanel;
    private boolean[][] isHidden;
    private boolean[][] isMarked;

    public FrameView(Controller controller) {
        this.controller = controller;
    }

    public void removeField() {
        if (centrePanel != null) {
            frame.remove(centrePanel);
        }
    }

    public void createField(int fieldWidth, int fieldHeight) {
        GridLayout centreLayout = new GridLayout(fieldHeight, fieldWidth);
        centrePanel = new JPanel(centreLayout);
        frame.add(centrePanel, BorderLayout.CENTER);

        GridBagConstraints p = new GridBagConstraints();

        p.anchor = GridBagConstraints.CENTER;
        p.fill = GridBagConstraints.BOTH;
        p.gridwidth = 1;
        p.gridheight = 1;
        p.gridx = GridBagConstraints.RELATIVE;
        p.gridy = GridBagConstraints.RELATIVE;
        p.insets = new Insets(0, 0, 0, 0);
        p.ipadx = 0;
        p.ipady = 0;
        p.weightx = 1.0;
        p.weighty = 1.0;

        for (int y = 0; y < fieldHeight; y++) {

            for (int x = 0; x < fieldWidth; x++) {
                GridBagLayout panelXYLayout = new GridBagLayout();
                JPanel panelXY = new JPanel(panelXYLayout);
                JButton buttonXY = new JButton();
                panelXYLayout.setConstraints(buttonXY, p);
                panelXY.add(buttonXY);
                centrePanel.add(panelXY);

                int finalX = x;
                int finalY = y;

                buttonXY.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == 1) {
                            panelXY.remove(buttonXY);
                            JLabel labelXY = new JLabel(controller.getTypeOfCell(finalX, finalY));
                            panelXYLayout.setConstraints(labelXY, p);
                            panelXY.add(labelXY);
                        }

                        if (e.getButton() == 3) {
                            Icon icon = new ImageIcon("ru/oop/nikolenko/minesweeper/resources/flag1.png");
                            buttonXY.setIcon(icon);
                            buttonXY.setMargin(new Insets(0, 0, 0, 0));
                        }

                        SwingUtilities.updateComponentTreeUI(frame);
                    }
                });
            }
        }

        SwingUtilities.updateComponentTreeUI(frame);
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {

            /*
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
             */

            final int frameDefaultWidth = 430;
            final int frameDefaultHeight = 550;
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            frame = new JFrame("Minesweeper");
            frame.setLocation((screenSize.width - frameDefaultWidth) / 2, (screenSize.height - frameDefaultHeight) / 2);
            frame.setSize(frameDefaultWidth, frameDefaultHeight);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            JMenuBar menuBar = MinesweeperMenuBar.createJMenuBar(controller);

            frame.setJMenuBar(menuBar);

            GridBagLayout pageStartLayout = new GridBagLayout();
            JPanel pageStartPanel = new JPanel(pageStartLayout);
            frame.add(pageStartPanel, BorderLayout.PAGE_START);

            GridBagConstraints c = new GridBagConstraints();

            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = GridBagConstraints.RELATIVE;
            c.gridy = GridBagConstraints.RELATIVE;
            c.insets = new Insets(0, 0, 0, 0);
            c.ipadx = 0;
            c.ipady = 0;
            c.weightx = 0.0;
            c.weighty = 0.0;

            JLabel emptyLabel = new JLabel();
            pageStartLayout.setConstraints(emptyLabel, c);
            pageStartPanel.add(emptyLabel);

            JButton smileButton = new JButton("Smile");
            pageStartLayout.setConstraints(smileButton, c);
            pageStartPanel.add(smileButton);

            smileButton.addActionListener(e -> controller.startNewGame());

            c.gridwidth = GridBagConstraints.REMAINDER;

            JLabel emptyLabel2 = new JLabel();
            pageStartLayout.setConstraints(emptyLabel2, c);
            pageStartPanel.add(emptyLabel2);
        });
    }
}
