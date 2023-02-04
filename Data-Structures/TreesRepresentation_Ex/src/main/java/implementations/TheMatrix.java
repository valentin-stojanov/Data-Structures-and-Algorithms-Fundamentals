package implementations;

import java.util.ArrayDeque;
import java.util.Deque;

public class TheMatrix {
    private final char[][] matrix;
    private final char fillChar;
    private final char startChar;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.startChar = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {
        bfsSolution(this.startRow, this.startCol);
//        dfsSolution();
//        dfs2(this.startRow, this.startCol);
    }

    public String toOutputString() {
        StringBuilder builder = new StringBuilder();

        for (int r = 0; r < this.matrix.length; r++) {
            for (int c = 0; c < this.matrix[r].length; c++) {
                builder.append(this.matrix[r][c]);
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private void dfsSolution() {
        if (!isInBounds(this.startRow, this.startCol)) {
            return;
        }

        this.matrix[startRow][this.startCol] = this.fillChar;
//        System.out.println(this.toOutputString());
//        System.out.println("------");

        if (isInBounds(this.startRow + 1, this.startCol) && this.matrix[this.startRow + 1][this.startCol] == this.startChar) {
            this.startRow++;
            dfsSolution();
            this.startRow--;
        }
        if (isInBounds(this.startRow, this.startCol + 1) && this.matrix[this.startRow][this.startCol + 1] == this.startChar) {
            this.startCol++;
            dfsSolution();
            this.startCol--;
        }
        if (isInBounds(this.startRow - 1, this.startCol) && this.matrix[this.startRow - 1][this.startCol] == this.startChar) {
            this.startRow--;
            dfsSolution();
            this.startRow++;
        }
        if (isInBounds(this.startRow, this.startCol - 1) && this.matrix[this.startRow][this.startCol - 1] == this.startChar) {
            this.startCol--;
            dfsSolution();
            this.startCol++;
        }

    }

    private boolean isInBounds(int currentRow, int currentCol) {
        return (0 <= currentRow && currentRow < this.matrix.length) && (0 <= currentCol && currentCol < this.matrix[currentRow].length);
    }

    private void dfs2(int row, int col) {
        if (!isInBounds(row, col) || this.matrix[row][col] != this.startChar) {
            return;
        }

        this.matrix[row][col] = this.fillChar;

        System.out.println(this.toOutputString());
        System.out.println("-----");

        this.dfs2(row + 1, col);
        this.dfs2(row, col + 1);
        this.dfs2(row - 1, col);
        this.dfs2(row, col - 1);
    }

    private void bfsSolution(int row, int col) {
        int[] coordinates = {row, col};
        Deque<int[]> queue = new ArrayDeque<>();

        queue.offer(coordinates);

        while (queue.size() > 0) {

            int[] currentCoordinates = queue.poll();
            int currRow = currentCoordinates[0];
            int currCol = currentCoordinates[1];


            this.matrix[currRow][currCol] = this.fillChar;

//            System.out.println(this.toOutputString());
//            System.out.println("-----");

            if (isInBounds(currRow + 1, currCol) && this.matrix[currRow + 1][currCol] == this.startChar) {
                queue.offer(new int[]{currRow + 1, currCol});
            }
            if (isInBounds(currRow, currCol + 1) && this.matrix[currRow][currCol + 1] == this.startChar) {
                queue.offer(new int[]{currRow, currCol + 1});
            }
            if (isInBounds(currRow - 1, currCol) && this.matrix[currRow - 1][currCol] == this.startChar) {
                queue.offer(new int[]{currRow - 1, currCol});
            }
            if (isInBounds(currRow, currCol - 1) && this.matrix[currRow][currCol - 1] == this.startChar) {
                queue.offer(new int[]{currRow, currCol - 1});
            }
        }
    }
}
