package com.xo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DrawField extends JFrame {
    public static final int DEFAULT_WIDTH = 900;
    public static final int DEFAULT_HEIGHT = 900;

    public DrawField(String title) {
        setTitle(title);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT + 50);
        DrawPanel drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
        add(new DrawButton(drawPanel), BorderLayout.SOUTH);
    }
}
    class DrawButton extends JPanel {
        public JButton startOver = new JButton("Начать заново");
        public JButton deleteTurn = new JButton("Отменить ход");
        DrawButton (DrawPanel drawPanel) {
            setLayout(new FlowLayout());
            add(startOver);
            add(deleteTurn);
            ActionListener al = new ButtonClick(drawPanel, this);
            startOver.addActionListener(al);
            deleteTurn.addActionListener(al);
        }
    }

    class DrawPanel extends JPanel  {
        public static final int DEFAULT_COUNT_CELLS = 3;
        static final int MIN_COORDINATA = 0;
        static final int MAX_COORDINATA = 2;
        static final int DEFAULT_VALUE = -1;
        static final int NOTHING_VALUE = -2;
        static final int DEFAULT_PEOPLE1_CHAR = 1;
        static final int DEFAULT_COMP_OR_PEOPLE2_CHAR = 0;
        final int stepCells;
        int cellCoordinataX = DEFAULT_VALUE;
        int cellCoordinataY = DEFAULT_VALUE;
        ClickHandling clickHandling;
        Turns turns = new Turns(DEFAULT_COUNT_CELLS, DEFAULT_COUNT_CELLS);
        TurnsList turnsList;
        DrawPanel() {
            stepCells = DrawField.DEFAULT_WIDTH / DEFAULT_COUNT_CELLS;
            clickHandling = new ClickHandling(this);
            addMouseListener(clickHandling);
            turnsList  = new TurnsList();
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            int [][] turnsArray = turns.getTurnsArray();

            for (int i = 1; i<DEFAULT_COUNT_CELLS; i++) {
                g.drawLine(stepCells * i, DrawField.DEFAULT_WIDTH, stepCells * i, MIN_COORDINATA);
                g.drawLine(MIN_COORDINATA, stepCells * i, DrawField.DEFAULT_WIDTH, stepCells * i );
            }
            for (int i = 0; i < DEFAULT_COUNT_CELLS; i++) {
                for (int j = 0; j < DEFAULT_COUNT_CELLS; j++) {
                    if (turnsArray[j][i] != DEFAULT_VALUE) {
                        switch (turnsArray[j][i]) {
                            case DEFAULT_PEOPLE1_CHAR:
                                g.drawLine(clickHandling.searchCoordinataConners(i), clickHandling.searchCoordinataConners(j), clickHandling.searchCoordinataConners(i) + stepCells, clickHandling.searchCoordinataConners(j) + stepCells);
                                g.drawLine(clickHandling.searchCoordinataConners(i) + stepCells, clickHandling.searchCoordinataConners(j), clickHandling.searchCoordinataConners(i), clickHandling.searchCoordinataConners(j) + stepCells);
                                break;
                            case DEFAULT_COMP_OR_PEOPLE2_CHAR:
                                g.drawOval(clickHandling.searchCoordinataConners(i), clickHandling.searchCoordinataConners(j), stepCells, stepCells);
                        }
                    }
                }
            }
            if ((cellCoordinataX != DEFAULT_VALUE) && (cellCoordinataY != DEFAULT_VALUE)) {
                g.drawLine(cellCoordinataX, cellCoordinataY, cellCoordinataX + stepCells, cellCoordinataY + stepCells);
                g.drawLine(cellCoordinataX + stepCells, cellCoordinataY, cellCoordinataX, cellCoordinataY + stepCells);
                //g.drawOval(cellCoordinataX, cellCoordinataY, stepCells, stepCells);
            }
        }
    }

