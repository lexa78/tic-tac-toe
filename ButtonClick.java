package com.xo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClick implements ActionListener {
    DrawPanel dp;
    DrawButton db;
    ButtonClick(DrawPanel drawPanel, DrawButton drawButton) {
        dp = drawPanel;
        db = drawButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == db.startOver) {
            dp.turns.setDefaultValue();
            dp.turnsList.clearTurnsList();
            dp.cellCoordinataX = dp.DEFAULT_VALUE;
            dp.cellCoordinataY = dp.DEFAULT_VALUE;
            dp.repaint();
        }
        else
        if (e.getSource() == db.deleteTurn) {
            int [] tempArr;
            for (int i = 0; i < 2; i++) {
                tempArr = dp.turnsList.fromStringToArray(dp.turnsList.getTurnsListLastValue());
                dp.turns.setCellTurnsArray(tempArr[0], tempArr[1], dp.DEFAULT_VALUE);
                dp.turnsList.deleteTurnsListLastValue();
            }
            dp.cellCoordinataX = dp.DEFAULT_VALUE;
            dp.cellCoordinataY = dp.DEFAULT_VALUE;
            dp.repaint();
        }
    }
}
