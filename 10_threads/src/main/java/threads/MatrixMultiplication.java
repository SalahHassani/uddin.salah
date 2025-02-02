package threads;

import java.util.Random;

public class MatrixMultiplication {

    int[][] matrixA;
    int[][] matrixB;
    int[][] matrixC;

    public void setMatrixA(int m, int n){
        matrixA = generateRandomMatrix(m, n);
    }

    public void setMatrixB(int n, int k){
        matrixB = generateRandomMatrix(n, k);
    }

    public void multiplyMatrices(){

        if(matrixA[0].length != matrixB.length) {
            throw new RuntimeException("Size Mismatch Error");
        }

        // Matrix C with size (m X k) = (2 X 4)
        matrixC = new int[matrixA.length][matrixB[0].length];

        // creating m threads for each row
        Thread[] mxtThreads = new Thread[matrixA.length];

        for (int i = 0; i < matrixA.length; i++) {

            int rowA = i;
            mxtThreads[i] = new Thread(() -> {

                for(int colB = 0; colB < matrixB[0].length; colB++) {
                    int colA = 0;
                    int sum = 0;

                    for (int rowB = 0; rowB < matrixB.length; rowB++) {
                        sum += matrixA[rowA][colA++] * matrixB[rowB][colB];
                    }
                    matrixC[rowA][colB] = sum;
                }
            });
        }

        for(Thread thread : mxtThreads) {
            thread.start();
        }

        try {
            for(Thread thread : mxtThreads) {
                thread.join();
            }
        }
        catch(InterruptedException e) {
            throw new RuntimeException("Matrix Threads interrupted.");
        }
    }

    public void print(String m) {
        switch (m) {
            case "A":
                printMatric(matrixA);
                break;
            case "B":
                printMatric(matrixB);
                break;
            case "C":
                printMatric(matrixC);
                break;
            default:
                System.out.println("Error: No such matrix");
        }
    }


    private void printMatric(int[][] mtx) {
        System.out.println("[");
        for (int i = 0; i < mtx.length; i++) {
            System.out.print("\t[");
            for (int j = 0; j < mtx[i].length; j++) {
                System.out.print(mtx[i][j]);
                if(j < mtx[i].length-1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    private int[][] generateRandomMatrix(int rows, int cols) {
        Random random = new Random();
        int max = 10;
        int min = 1;
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt((max - min) + 1) + min; // Random value between min and max
            }
        }

        return matrix;
    }

}
