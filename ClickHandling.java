package com.xo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickHandling implements MouseListener {
    private DrawPanel field;

    public ClickHandling (DrawPanel parent) {
        field = parent;
    }

    public int firstElement = DrawPanel.DEFAULT_VALUE;

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

        vacantCell = DrawPanel.DEFAULT_COUNT_CELLS/2;
        if (field.turns.getCellValue(vacantCell, vacantCell) == DrawPanel.DEFAULT_VALUE) {
            field.turns.setCellTurnsArray(vacantCell, vacantCell, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
            field.turnsList.setTurnsListValue(togetherInStirng(vacantCell, vacantCell));
            return;
        }
        else {
            if (field.turnsList.getSize() == DrawPanel.DEFAULT_COUNT_CELLS) {
                int [] coordinatArr= field.turnsList.fromStringToArray(field.turnsList.getTurnsListLastValue());
                if ((coordinatArr[0] == DrawPanel.MAX_COORDINATA)&&(coordinatArr[1] == DrawPanel.MAX_COORDINATA)) {
                    if (field.turns.getCellValue(DrawPanel.MIN_COORDINATA, coordinatArr[0]) == DrawPanel.DEFAULT_VALUE) {
                        field.turns.setCellTurnsArray(coordinatArr[0], DrawPanel.MIN_COORDINATA, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                        field.turnsList.setTurnsListValue(togetherInStirng(coordinatArr[0], DrawPanel.MIN_COORDINATA));
                        return;
                    }
                }
                else
                if ((coordinatArr[0] == DrawPanel.MIN_COORDINATA)&&(coordinatArr[1] == DrawPanel.MIN_COORDINATA)) {
                    if (field.turns.getCellValue(DrawPanel.MAX_COORDINATA, coordinatArr[0]) == DrawPanel.DEFAULT_VALUE) {
                        field.turns.setCellTurnsArray(coordinatArr[0], DrawPanel.MAX_COORDINATA, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                        field.turnsList.setTurnsListValue(togetherInStirng(coordinatArr[0], DrawPanel.MAX_COORDINATA));
                        return;
                    }
                }
                else
                if ((coordinatArr[0] == DrawPanel.MAX_COORDINATA)&&(coordinatArr[1] == DrawPanel.MIN_COORDINATA)) {
                    if (field.turns.getCellValue(DrawPanel.MAX_COORDINATA, coordinatArr[0]) == DrawPanel.DEFAULT_VALUE) {
                        field.turns.setCellTurnsArray(coordinatArr[0], DrawPanel.MAX_COORDINATA, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                        field.turnsList.setTurnsListValue(togetherInStirng(coordinatArr[0], DrawPanel.MAX_COORDINATA));
                        return;
                    }
                }
                else
                if ((coordinatArr[0] == DrawPanel.MIN_COORDINATA)&&(coordinatArr[1] == DrawPanel.MAX_COORDINATA)) {
                    if (field.turns.getCellValue(coordinatArr[1], DrawPanel.MAX_COORDINATA) == DrawPanel.DEFAULT_VALUE) {
                        field.turns.setCellTurnsArray(DrawPanel.MAX_COORDINATA, coordinatArr[1], DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                        field.turnsList.setTurnsListValue(togetherInStirng(DrawPanel.MAX_COORDINATA, coordinatArr[1]));
                        return;
                    }
                }
                else {
                    if (findEmptyCell()) {
                        return;
                    }
                }
            }
            else {
                if (findEmptyCell()) {
                    return;
                }
            }
        }
    }
    private boolean findEmptyCell() {
        int vacantCell;
        for (int i = 0; i < DrawPanel.DEFAULT_COUNT_CELLS; i++) {
            vacantCell = field.turns.searchEmptyCell(i);
            if (vacantCell != DrawPanel.NOTHING_VALUE) {
                field.turns.setCellTurnsArray(vacantCell, i, DrawPanel.DEFAULT_COMP_OR_PEOPLE2_CHAR);
                field.turnsList.setTurnsListValue(togetherInStirng(vacantCell, i));
                return true;
            }
        }
        return false;
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
                field.turnsList.setTurnsListValue(togetherInStirng(x,y));
                return true;
            }
        }
        return false;
    }

    String togetherInStirng (int x, int y) {
        return "" + x + "," + y;
    }

    boolean gameOverControl() {
        for (int i = 0; i < DrawPanel.DEFAULT_COUNT_CELLS; i++) {
            if ((getLineForGameOverControl(field.turns.getTurnsArrayHorizontal(i))) ||
               (getLineForGameOverControl(field.turns.getTurnsArrayVertical(i)))) {
                    return true;
            }
        }
        if ((getLineForGameOverControl(field.turns.getDiagonal())) ||
            (getLineForGameOverControl(field.turns.getReverseDiagonal()))) {
                return true;
        }
        return false;
    }

    boolean getLineForGameOverControl(int [] line) {
        boolean flag = false;
        firstElement = line[0];
        for (int i = 1; i < DrawPanel.DEFAULT_COUNT_CELLS; i++) {
            if ((firstElement == line[i])&&(firstElement != DrawPanel.DEFAULT_VALUE)) {
                flag = true;
            }
            else {
                return false;
            }
        }
        return flag;
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String winner;
        field.cellCoordinataY = searchCoordinataConners(searchCell(y));
        field.cellCoordinataX = searchCoordinataConners(searchCell(x));
        if (field.turns.getCellValue(searchCell(y), searchCell(x)) == DrawPanel.DEFAULT_VALUE) {
            field.turns.setCellTurnsArray(searchCell(x), searchCell(y), DrawPanel.DEFAULT_PEOPLE1_CHAR);
            field.turnsList.setTurnsListValue(togetherInStirng(searchCell(x), searchCell(y)));
        } else {
            JOptionPane.showMessageDialog(null, "Сюда уже ничего не поставить");
            return;
        }
        if (field.turnsList.getSize() < DrawPanel.DEFAULT_COUNT_CELLS * DrawPanel.DEFAULT_COUNT_CELLS) {
            if (!gameOverControl()) {
                compThinking();
            }
        } else {
            field.repaint();
            winner = "No winner";
            JOptionPane.showMessageDialog(null, "Game over " + System.getProperty("line.separator") + winner);
            return;
        }
        if (field.turnsList.getSize() >= DrawPanel.DEFAULT_COUNT_CELLS * 2 - 1) {
            if (gameOverControl()) {
                field.repaint();
                if (firstElement == DrawPanel.DEFAULT_PEOPLE1_CHAR) {
                    winner = "You win!";
                } else {
                    winner = "You loose, comp win!!!";
                }
                JOptionPane.showMessageDialog(null, "Game over " + System.getProperty("line.separator") + winner);
                return;
            } else {
                field.repaint();
            }
        } else {
            field.repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {}
}