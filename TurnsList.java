package com.xo;

import java.util.ArrayList;

public class TurnsList {
    private ArrayList <String> turnsList = new ArrayList<String>();

    void setTurnsListValue(String turn) {
        turnsList.add(turn);
    }

    void clearTurnsList() {
        turnsList.clear();
    }

    String getTurnsListLastValue() {
        return turnsList.get(turnsList.size()-1);
    }

    void deleteTurnsListLastValue() {
        turnsList.remove(turnsList.size()-1);
    }

    int getSize() {
        return turnsList.size();
    }

    int [] fromStringToArray (String str) {
        String [] strArr = str.split(",");
        int [] returnedArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            returnedArr[i] = Integer.parseInt(strArr[i]);
        }
        return returnedArr;
    }
@Override
    public String toString () {
        String str="";
        for (int i = 0; i < turnsList.size(); i++) {
            str += turnsList.get(i)+"   ";
        }
        return str;
    }
}
