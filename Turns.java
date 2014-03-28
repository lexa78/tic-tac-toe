package com.xo;

public class Turns {
    private int [] [] turnsArray = new int[DrawPanel.DEFAULT_COUNT_CELLS][DrawPanel.DEFAULT_COUNT_CELLS];
    public Turns (int sizeX, int sizeY) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                turnsArray[i][j] = DrawPanel.DEFAULT_VALUE;
            }
        }
    }

    void setCellTurnsArray(int cellX, int cellY, int value) {
        turnsArray [cellY][cellX] = value;
    }

    int[][] getTurnsArray() {
        return turnsArray;
    }

    int [] getTurnsArrayHorizontal(int i) {
        return turnsArray[i];
    }

    int [] getTurnsArrayVertical(int i) {
        int [] verticalArray = new int[DrawPanel.DEFAULT_COUNT_CELLS];
        for (int j = 0; j < DrawPanel.DEFAULT_COUNT_CELLS; j++) {
            verticalArray[j] = turnsArray[j][i];
        }
        return verticalArray;
    }

    int [] getDiagonal() {
        int [] diagonalArray = new int[DrawPanel.DEFAULT_COUNT_CELLS];
        for (int i = 0; i < DrawPanel.DEFAULT_COUNT_CELLS; i++) {
            diagonalArray[i] = turnsArray[i][i];
        }
        return diagonalArray;
    }

    int [] getReverseDiagonal() {
        int [] reverseDiagonalArray = new int[DrawPanel.DEFAULT_COUNT_CELLS];
        for (int i = 0, j = 1; i < DrawPanel.DEFAULT_COUNT_CELLS; i++, j++) {
            reverseDiagonalArray[i] = turnsArray[i][turnsArray.length - j];
        }
        return reverseDiagonalArray;
    }

    int getCellValue(int i, int j) {
        return turnsArray[i][j];
    }

    int searchEmptyCell(int k) {
        int [] line = getTurnsArrayHorizontal(k);
        for (int i = 0; i < DrawPanel.DEFAULT_COUNT_CELLS; i++) {
            if (line[i] == DrawPanel.DEFAULT_VALUE) {
                return i;
            }
        }
        return DrawPanel.NOTHING_VALUE;
    }

/*    void showArr () {
        for (int i = 0; i < turnsArray.length; i++) {
            for (int j = 0; j < turnsArray.length; j++) {
                System.out.print(" ["+turnsArray[i][j]+"] ");
            }
            System.out.println();
        }
    }*/
}
