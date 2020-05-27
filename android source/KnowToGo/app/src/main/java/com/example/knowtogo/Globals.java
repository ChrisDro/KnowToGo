package com.example.knowtogo;

import android.app.Application;
import android.bluetooth.BluetoothSocket;

import java.util.ArrayList;

public class Globals extends Application {
    public static final int EASY_MODE = 1;
    public static final int MEDIUM_MODE = 2;
    public static final int HARD_MODE = 3;

    private BluetoothSocket btSocket = null;
    private ArrayList<String> operators = new ArrayList<String>();
    private int difficulty = -1;
    private int sessionLength = -1;
    private int finalScore = -1;

    public BluetoothSocket getBtSocket() {
        return btSocket;
    }
    public void setBtSocket(BluetoothSocket btSocket) {
        this.btSocket = btSocket;
    }
    public ArrayList<String> getOperators() {
        return operators;
    }
    public void addOperator(String operator) {
        this.operators.add(operator);
    }
    public void clearOperators(){ this.operators.clear(); }
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public int getSessionLength() {
        return sessionLength;
    }
    public void setSessionLength(int sessionLength) {
        this.sessionLength = sessionLength;
    }
    public int getFinalScore() {
        return finalScore;
    }
    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
}
