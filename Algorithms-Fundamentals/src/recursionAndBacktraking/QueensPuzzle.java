package recursionAndBacktraking;

import java.util.Scanner;

public class QueensPuzzle {
    static char[][] board = {
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'}
    };

    public static void main(String[] args) {


//        printSolution(board);

        findQueenPosition(0);

    }

    private static void findQueenPosition(int row) {
        if (row == 8) {
            printSolution(board);

        } else {
            for (int col = 0; col < 8; col++) {
                if (canPlaceQueen(row, col)) {
                    putQueen(row, col);
                    findQueenPosition(row + 1);
                    removeQueen(row, col);
                }
            }
        }

    }

    private static void removeQueen(int row, int col) {
        board[row][col] = '-';
    }

    private static void putQueen(int row, int col) {
        board[row][col] = '*';
    }

    private static boolean canPlaceQueen(int row, int col) {
        for (int c = 0; c < 8; c++) {
            if (c == col){
                continue;
            }
            if (board[row][c] == '*') {
                return false;
            }
        }
        for (int r = 0; r < 8; r++) {
            if (r == row){
                continue;
            }
            if (board[r][col] == '*') {
                return false;
            }
        }

        int r = row , c = col ;

        while (r >= 0 && c >= 0) {
            if (board[r--][c--] == '*') {
                return false;
            }
        }

        r = row ;
        c = col ;

        while (r < 8 && c < 8) {
            if (board[r++][c++] == '*') {
                return false;
            }

        }

        r = row ;
        c = col ;

        while (r >= 0 && c < 8) {
            if (board[r--][c++] == '*') {
                return false;
            }
        }

        r = row ;
        c = col ;

        while (r < 8 && c >= 0) {
            if (board[r++][c--] == '*') {
                return false;
            }
        }
        return true;
    }


    private static void printSolution(char[][] board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
