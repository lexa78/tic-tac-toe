package com.xo;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickHandling implements MouseListener {
    private DrawPanel field;

    public ClickHandling (DrawPanel parent) {
        field = parent;
    }
    private int searchCell(int x) {
        int i =0;
        int count = 0;
        while (true) {
            if ((x > i) && (x < (i += field.stepCells))) {
                return count;
            }
            count++;
        }
    }
    int searchCoordinataConners (int num) {
        switch (num) {
            case 0:
                return DrawPanel.MIN_COORDINATA;
            case 1:
                return field.stepCells;
            case 2:
                return field.stepCells*num;
        }
        return DrawPanel.NOTHING_VALUE;
    }
    private void compThinking() {

        int vacantCell;

        if ((analyseAndTurn (DrawPanel.DEFAULT_COUNT_CELLS, true, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR)) ||
            (analyseAndTurn (DrawPanel.DEFAULT_COUNT_CELLS, false, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR)) ||
            (analyseAndTurn (1, true, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR)) ||
            (analyseAndTurn (1, false, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR)) ||
            (analyseAndTurn (DrawPanel.DEFAULT_COUNT_CELLS, true, DrawPanel.DEFAULT_PEOPLE1_CHAR)) ||
            (analyseAndTurn (DrawPanel.DEFAULT_COUNT_CELLS, false, DrawPanel.DEFAULT_PEOPLE1_CHAR)) ||
            (analyseAndTurn (1, true, DrawPanel.DEFAULT_PEOPLE1_CHAR)) ||
            (analyseAndTurn (1, false, DrawPanel.DEFAULT_PEOPLE1_CHAR)) ) {
            return;
        }


/*        for (int i = 0; i < DrawPanel.DEFAULT_COUNT_CELLS; i++) {
            column = field.turns.getTurnsArrayHorizontal(i);
            vacantCell = getRowForAnalyse(column, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
            if (vacantCell == DrawPanel.NOTHING_VALUE) {
                vacantCell = getRowForAnalyse(column, DrawPanel.DEFAULT_PEOPLE1_CHAR);
            }
            if (vacantCell != DrawPanel.NOTHING_VALUE) {
                field.turns.setCellTurnsArray(vacantCell, i, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                return;
            }
        }

        for (int i = 0; i < DrawPanel.DEFAULT_COUNT_CELLS; i++) {
            column = field.turns.getTurnsArrayVertical(i);
            vacantCell = getRowForAnalyse(column, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
            if (vacantCell == DrawPanel.NOTHING_VALUE) {
                vacantCell = getRowForAnalyse(column, DrawPanel.DEFAULT_PEOPLE1_CHAR);
            }
            if (vacantCell != DrawPanel.NOTHING_VALUE) {
                field.turns.setCellTurnsArray(i, vacantCell, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                return;
            }
        }

        column = field.turns.getDiagonal();
        vacantCell = getRowForAnalyse(column, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
        if (vacantCell == DrawPanel.NOTHING_VALUE) {
            vacantCell = getRowForAnalyse(column, DrawPanel.DEFAULT_PEOPLE1_CHAR);
        }
        if (vacantCell != DrawPanel.NOTHING_VALUE) {
            field.turns.setCellTurnsArray(vacantCell, vacantCell, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
            return;
        }

        column = field.turns.getReverseDiagonal();
        vacantCell = getRowForAnalyse(column, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
        if (vacantCell == DrawPanel.NOTHING_VALUE) {
            vacantCell = getRowForAnalyse(column, DrawPanel.DEFAULT_PEOPLE1_CHAR);
        }
        if (vacantCell != DrawPanel.NOTHING_VALUE) {
            switch(vacantCell) {
                case 0:
                    field.turns.setCellTurnsArray(2, vacantCell, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                    return;
                case 1:
                    field.turns.setCellTurnsArray(vacantCell, vacantCell, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                    return;
                case 2:
                    field.turns.setCellTurnsArray(0, vacantCell, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                    return;
            }
        }*/

        vacantCell = DrawPanel.DEFAULT_COUNT_CELLS/2;
        if (field.turns.getCellValue(vacantCell, vacantCell) == DrawPanel.DEFAULT_VALUE) {
            field.turns.setCellTurnsArray(vacantCell, vacantCell, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
            return;
        }
        else {
            for (int i = 0; i < DrawPanel.DEFAULT_COUNT_CELLS; i++) {
                vacantCell = field.turns.searchEmptyCell(i);
                if (vacantCell != DrawPanel.NOTHING_VALUE) {
                    field.turns.setCellTurnsArray(vacantCell, i, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                    return;
                }
            }
        }
    }
    private int getRowForAnalyse (int [] row, int symbol) {
        int count = 0;
        int rememberI = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] == symbol) {
                count++;
            }
            else {
                if (row[i] == DrawPanel.DEFAULT_VALUE) {
                    rememberI = i;
                }
                else {
                    rememberI = DrawPanel.NOTHING_VALUE;
                }
            }
        }
        if (count == DrawPanel.DEFAULT_COUNT_CELLS - 1) {
            return rememberI;
        }
        else {
            return DrawPanel.NOTHING_VALUE;
        }
    }

    private boolean analyseAndTurn (int loop, boolean flag, int xORo) {
        int [] column;
        int vacantCell, x, y;
        for (int i = 0; i < loop; i++) {
            if (loop > 1) {
                if (flag) {
                    column = field.turns.getTurnsArrayHorizontal(i);
                }
                else {
                    column = field.turns.getTurnsArrayVertical(i);
                }
            }
            else {
                if (flag) {
                    column = field.turns.getDiagonal();
                }
                else {
                    column = field.turns.getReverseDiagonal();
                }
            }
            vacantCell = getRowForAnalyse(column, xORo);

            if (vacantCell != DrawPanel.NOTHING_VALUE) {
                if (loop > 1) {
                    if (flag) {
                        x = vacantCell;
                        y = i;
                    } else {
                        y = vacantCell;
                        x = i;
                    }
                }
                else {
                    if (flag) {
                        x = y = vacantCell;
                    }
                    else {
                        if (vacantCell == DrawPanel.MAX_COORDINATA) {
                            x = DrawPanel.MIN_COORDINATA;
                            y = vacantCell;
                        }
                        else if (vacantCell == DrawPanel.MIN_COORDINATA) {
                            x = DrawPanel.MAX_COORDINATA;
                            y = vacantCell;
                        }
                        else {
                            x = y = vacantCell;
                        }
                    }
                }
                field.turns.setCellTurnsArray(x, y, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                return true;
            }
        }
        return false;
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        field.cellCoordinataY = searchCoordinataConners(searchCell(y));
        field.cellCoordinataX = searchCoordinataConners(searchCell(x));
        if (field.turns.getCellValue(searchCell(y), searchCell(x)) == DrawPanel.DEFAULT_VALUE) {
            field.turns.setCellTurnsArray(searchCell(x), searchCell(y), DrawPanel.DEFAULT_PEOPLE1_CHAR);
        }
        else {
            JOptionPane.showMessageDialog(null, "Сюда уже ничего не поставить");
            return;
        }
        compThinking();
        field.repaint();
    }

    public void mouseReleased(MouseEvent e) {}
}