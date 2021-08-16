package com.titactoe.omar;

import java.io.Serializable;

public class MyMatrixAlgorithm implements Serializable {

    private int[][] matrix;

    public MyMatrixAlgorithm() {
        matrix = new int[3][3];
    }

    public void set(int rowIndex, int colIndex, int data) {
        matrix[rowIndex][colIndex] = data;
    }

    public int get(int rowIndex, int colIndex) {
        return matrix[rowIndex][colIndex];
    }
}


